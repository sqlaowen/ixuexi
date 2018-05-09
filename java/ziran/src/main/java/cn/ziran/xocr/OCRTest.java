package cn.ziran.xocr;

import java.io.File;

import cn.easyproject.easyocr.EasyOCR;
import cn.easyproject.easyocr.ImageType;

public class OCRTest {

  public static void main(String[] args) {
    EasyOCR e = new EasyOCR();
    File f = new File("D:/xx.jpg");
    e.discern(f);
    e.discernAndAutoCleanImage(f, ImageType.CAPTCHA_HOLLOW_CHAR);

  }
}
