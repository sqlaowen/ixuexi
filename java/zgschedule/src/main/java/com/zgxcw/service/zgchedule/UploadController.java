package com.zgxcw.service.zgchedule;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.catalina.loader.WebappClassLoader;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

@Controller
public class UploadController {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @ResponseBody
  @RequestMapping(value = "/upfile", method = RequestMethod.POST)
  public String handleImport(Model model, DefaultMultipartHttpServletRequest multipartRequest)
      throws IOException {
    if (multipartRequest != null) {
      Iterator<String> iterator = multipartRequest.getFileNames();
      int i = 0;
      while (iterator.hasNext()) {
        MultipartFile multifile = multipartRequest.getFile((String) iterator.next());
        
        if (multifile.getOriginalFilename().length() > 0) {
          String [] strList=multifile.getOriginalFilename().split("\\.");
          if(!strList[strList.length-1].toLowerCase().equals("jar"))
            return "只允许上传jar文件...";
          
          String spt = File.separator;// 路径分割符
          String pathName =
              multipartRequest.getSession().getServletContext().getRealPath("/") + spt + "jar";
          File localFileDir = new File(pathName);
          if (!localFileDir.exists())
            localFileDir.mkdirs();
          SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
          String filePath =
              pathName + spt + sdf.format(new Date()) + "-" + multifile.getOriginalFilename();
          File localFile = new File(filePath);
          // 复制临时文件到指定目录下
          FileUtils.copyInputStreamToFile(multifile.getInputStream(), localFile);
          log.debug("jar文件上传成功【"+filePath+"】...");
          WebappClassLoader loader = (WebappClassLoader) getClass().getClassLoader();
          loader.addRepository(localFile.toURI().toURL().toString());
          log.debug("jar文件加载成功...");
          i++;
        }
      }
      if (i > 0)
        return "success";
      return "没有上传文件...";
    }
    return "没有上传文件...";
  }
}
