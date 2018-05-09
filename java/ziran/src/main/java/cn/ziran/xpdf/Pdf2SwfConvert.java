package cn.ziran.xpdf;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.ziran.xutil.XContextUtil;

public class Pdf2SwfConvert {
  private static Logger log = LoggerFactory.getLogger(Pdf2SwfConvert.class);

  /**
   * pdf2swf
   * @param inputFile
   * @param swfFile
   */
  public static void pdf2swf(String inputFile, String swfFile) {
    File pdfFile = new File(inputFile);
    File outFile = new File(swfFile);
    if (!pdfFile.exists()) {
      log.error("Pdf2SwfConvert.pdf2Swf pdf文件不存在...");
      return;
    }
    if (outFile.exists()) {
      log.error("Pdf2SwfConvert.pdf2Swf swf文件已存在...");
      return;
    }
    
    String command =XContextUtil.getRequest().getContextPath()+"/resource/swftools/pdf2swf.exe " + inputFile + " -o " + swfFile + " -f -T 9";
    try {
      Process process = Runtime.getRuntime().exec(command);
      process.waitFor();
    } catch (IOException | InterruptedException e) {
      log.error("Pdf2SwfConvert.pdf2Swf转换异常:{}", e.getMessage());
      e.printStackTrace();
    }
  }
}
