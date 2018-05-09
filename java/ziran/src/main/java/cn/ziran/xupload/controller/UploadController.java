package cn.ziran.xupload.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @RequestMapping(value = "/upload", method = {RequestMethod.GET, RequestMethod.POST})
  public ModelAndView upload() {
    logger.info("get 请求");
    ModelAndView mv = new ModelAndView();
    mv.setViewName("/upload/index");
    Date date = new Date();
    mv.addObject("reqTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
    return mv;
  }

  @RequestMapping(value = "/upfile", method = RequestMethod.POST)
  public String handleImport(Model model, DefaultMultipartHttpServletRequest multipartRequest)
      throws IOException {

    if (multipartRequest != null) {
      Iterator<String> iterator = multipartRequest.getFileNames();

      while (iterator.hasNext()) {
        MultipartFile multifile = multipartRequest.getFile((String) iterator.next());

        if (multifile.getOriginalFilename().length() > 0) {
          System.out.println(multifile.getOriginalFilename());
          System.out.println(multifile.getContentType());
          System.out.println(multifile.getName());
          System.out.println(multifile.getSize());
          // 复制临时文件到指定目录下
          File f = new File("D:/Apps/" + multifile.getOriginalFilename());
          FileUtils.copyInputStreamToFile(multifile.getInputStream(), f);
        }
      }
    }
    // model可以传到前台页面上去
    model.addAttribute("result", "success");
    // forward和redirect区别：页面地址不一样
    // 客户端转发到视图 /upload
    // return "redirect:/upload";
    // 服务器转发到视图 /upload
    return "forward:/upload";
  }

  @ResponseBody
  @RequestMapping(value = "upfile2")
  public String upLoad2(HttpServletRequest request) throws IllegalStateException, IOException {
    // 解析器解析request的上下文
    CommonsMultipartResolver multipartResolver =
        new CommonsMultipartResolver(request.getSession().getServletContext());
    if (multipartResolver.isMultipart(request)) {
      MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
      Iterator<String> iter = multiRequest.getFileNames();
      while (iter.hasNext()) {
        MultipartFile file = multiRequest.getFile((String) iter.next());
        if (file != null) {
          String fileName = file.getOriginalFilename();
          String path = "D:/" + fileName;
          File localFile = new File(path);
          // 写文件到本地
          file.transferTo(localFile);
        }
      }
    }
    return "/success";
  }

}
