package cn.ziran.xupload.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.ueditor.ActionEnter;

@Controller
@RequestMapping("/ue")
public class UEController {

  @RequestMapping("/index")
  public String index() {

    return "/ue/index";
  }

  // 初始化UE
  @ResponseBody
  @RequestMapping("/init")
  public String initUE(@RequestParam(value = "action") String action, HttpServletResponse response,
      HttpServletRequest request) {
    String rootPath = request.getSession().getServletContext().getRealPath("/");
    return new ActionEnter(request, rootPath).exec();
  }
}
