package com.zgxcw.wx.web;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.zgxcw.framework.tfs.service.TfsService;
import com.zgxcw.wx.domain.UploadFile;

/**
 * Created by huolh on 2016/3/9.
 */
@Controller
@RequestMapping("/wx/upload")
public class WXUploadController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  // tfs服务
  @Autowired
  private TfsService tfsService;


  @ResponseBody
  @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
  public String uploadFile(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    UploadFile fileBean = upload(request, response);
    return  JSON.toJSONString(fileBean);
  }



  // 图片上传
  public UploadFile upload(HttpServletRequest request, HttpServletResponse response) {

    UploadFile fileBean = new UploadFile();
    fileBean.setCode("ok");

    // 转型为MultipartHttpRequest：
    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    // 获得文件：
    MultipartFile multipartFile = multipartRequest.getFile("picFile");
    // 获得文件名
    String originalFilename = multipartFile.getOriginalFilename(); // 文件全名
    String suffix = StringUtils.substringAfter(originalFilename, "."); // 后缀
    // 图片文件名: UUID
    String fileName = UUID.randomUUID().toString()+ "."+ suffix;

    String path = request.getSession().getServletContext().getRealPath("upload");

    // 文件夹
    File targetFile = new File(path);
    if (!targetFile.exists()) {
      boolean mkdirFlag = targetFile.mkdirs();
      if (!mkdirFlag) {
        fileBean.setCode("fail");
        fileBean.setMessage("创建图片上传目录失败");
        logger.warn("创建图片上传目录失败");
        return fileBean;
      }
    }

    if (targetFile.exists()) {
      // 文件存储全路径
      String realPath = path + File.separator + fileName;
      File uploadFile = new File(realPath);

      // 保存到本地
      try {
        FileCopyUtils.copy(multipartFile.getBytes(), uploadFile);
      } catch (Exception e) {
        logger.error("图片上传到服务器失败:"+e.toString());
        fileBean.setCode("fail");
        fileBean.setMessage("图片上传到服务器失败:"+e.toString());
      }

      // 保存到tfs
      String tfsFileName = tfsService.saveFile(uploadFile.getAbsolutePath(), null, null);
      fileBean.setRealPath(tfsFileName);

      // 删除图片
      if (uploadFile.exists()) {
        boolean deleteFlag = uploadFile.delete();
        if (!deleteFlag) {
          logger.warn("删除已上传的图片失败");
        }
      }
    }
    return fileBean;
  }
}
