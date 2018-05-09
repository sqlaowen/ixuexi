package cn.ziran.xpdf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pdf")
public class PDFController {

  @RequestMapping("/view")
  public String view(){
    return "/pdf/view";
  }
}
