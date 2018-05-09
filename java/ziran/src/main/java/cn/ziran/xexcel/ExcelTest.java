package cn.ziran.xexcel;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelTest {

  public static void main(String[] args) throws Exception {
    createExcel();
    readExcel();
  }

  public static void createExcel() throws Exception {
    Workbook workbook = null;// new HSSFWorkbook(); //xls
    workbook = new XSSFWorkbook();// xlsx
    Sheet sheet1 = workbook.createSheet();
    workbook.createSheet("sheet2");
    // 第二行
    Row row = sheet1.createRow(1);
    // 第二行第三个单元格
    Cell cell = row.createCell(2);
    cell.setCellValue("中华人民共和国");

    //////////////////// 样式///////////////////////////
    // 1.2创建单元格样式
    CellStyle style = workbook.createCellStyle();
    style.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
    // 1.3设置字体
    Font font = workbook.createFont();
    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    font.setFontHeightInPoints((short) 16);
    // 加载字体
    style.setFont(font);
    // 单元格背景
    // style.setFillPattern(CellStyle.DIAMONDS);//填充模式
    // style.setFillBackgroundColor(Color.YELLOW.index);//背景色
    cell.setCellStyle(style);


    //////////////////// 合并///////////////////////////
    // 第4行5行,从第3到第5单元格合并 , ============写入值
    Row row4 =sheet1.createRow(3);
    Cell cellxx= row4.createCell(2);
    cellxx.setCellStyle(style);
    cellxx.setCellValue("美利坚合众国");
    // 第4行5行,从第3到第5单元格合并
    CellRangeAddress cellRangeAddress = new CellRangeAddress(3, 4, 2, 4);
    sheet1.addMergedRegion(cellRangeAddress);
    
    row4.createCell(5).setCellValue("xxxx");
   

    // 输出到流中
    // ByteArrayOutputStream stream=new ByteArrayOutputStream();
    // workbook.write(stream);

    FileOutputStream fileOutputStream = new FileOutputStream("D:\\XX.xls");
    // 吧excel输入到具体的地址
    workbook.write(fileOutputStream);
    // 关闭
    workbook.close();
    fileOutputStream.close();
    System.out.println("excel输出成功");
  }

  public static void readExcel() throws Exception {
    FileInputStream fileInputStream = new FileInputStream("D:\\XX.xls");
    Workbook workbook = null;// new HSSFWorkbook(fileInputStream);//xls
    workbook = new XSSFWorkbook(fileInputStream);// xlsx
    Sheet sheet1 = workbook.getSheetAt(0);

    // 第二行
    Row row = sheet1.getRow(1);
    // 第二行第三个单元格
    Cell cell = row.getCell(2);

    System.out.printf("第二行三列的值:%s", cell.getStringCellValue());
    System.out.println();
    System.out.printf("第四行三列的值:%s", sheet1.getRow(3).getCell(2).getStringCellValue());
    // 关闭
    workbook.close();
    fileInputStream.close();
  }
}
