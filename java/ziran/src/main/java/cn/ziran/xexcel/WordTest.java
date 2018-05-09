package cn.ziran.xexcel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

public class WordTest {

  // Apache poi的hwpf模块是专门用来对word doc文件进行读写操作的。在hwpf里面我们使用HWPFDocument来表示一个word
  // doc文档。在HWPFDocument里面有这么几个概念：
  // Range：它表示一个范围，这个范围可以是整个文档，也可以是里面的某一小节（Section），也可以是某一个段落（Paragraph），还可以是拥有共同属性的一段文本（CharacterRun）。
  // Section：word文档的一个小节，一个word文档可以由多个小节构成。
  // Paragraph：word文档的一个段落，一个小节可以由多个段落构成。
  // CharacterRun：具有相同属性的一段文本，一个段落可以由多个CharacterRun组成。
  // Table：一个表格。
  // TableRow：表格对应的行。
  // TableCell：表格对应的单元格。
  // Section、Paragraph、CharacterRun和Table都继承自Range。

  // POI在读写word
  // docx文件时是通过xwpf模块来进行的，其核心是XWPFDocument。一个XWPFDocument代表一个docx文档，其可以用来读docx文档，也可以用来写docx文档。XWPFDocument中主要包含下面这几种对象：
  // XWPFParagraph：代表一个段落。
  // XWPFRun：代表具有相同属性的一段文本。
  // XWPFTable：代表一个表格。
  // XWPFTableRow：表格的一行。
  // XWPFTableCell：表格对应的一个单元格。

  public static void main(String[] args) throws Exception {
    WordTest wt = new WordTest();
    //wt.testReadByDoc();
    System.out.println("------------------------------------");
    wt.testSimpleWrite();
//    System.out.println("------------------------------------");
//    wt.testWriteTable();
//    System.out.println("------------------------------------");
//    wt.testTemplateWrite();
  }

  @SuppressWarnings("resource")
  public void testReadByDoc() throws Exception {
    InputStream is = new FileInputStream("D:\\test.docx");
    XWPFDocument doc = new XWPFDocument(is);
    //或者
    doc = new XWPFDocument(POIXMLDocument.openPackage("D:\\test.docx"));
    List<XWPFParagraph> paras = doc.getParagraphs();
    for (XWPFParagraph para : paras) {
      // 当前段落的属性
      // CTPPr pr = para.getCTP().getPPr();
      para.getPictureText();
      System.out.println(para.getText());
      System.out.println("<<<<<<<<<");
    }
   
    //图片
    List<XWPFPictureData> pics= doc.getAllPictures();
    System.out.println(pics.size());
    for (XWPFPictureData xwpfPictureData : pics) {
      byte[] bss=xwpfPictureData.getData();
      //System.out.println(Base64.encode(bss));
      FileOutputStream fileOutputStream = new FileOutputStream("D:\\xx.jpg");
      fileOutputStream.write(bss);
      fileOutputStream.flush();
      ByteArrayOutputStream stream=new ByteArrayOutputStream();
      stream.write(bss);
      byte[] bss2= stream.toByteArray();
      System.out.println("xxxxxxxx");
      //System.out.println(Base64.encode(stream.toByteArray()));
    }

    System.out.println("==========================================");
    // 获取文档中所有的表格
    List<XWPFTable> tables = doc.getTables();
    List<XWPFTableRow> rows;
    List<XWPFTableCell> cells;
    for (XWPFTable table : tables) {
      // 表格属性
      // CTTblPr pr = table.getCTTbl().getTblPr();
      // 获取表格对应的行
      rows = table.getRows();
      for (XWPFTableRow row : rows) {
        // 获取行对应的单元格
        cells = row.getTableCells();
        for (XWPFTableCell cell : cells) {
          System.out.println(cell.getText());;
        }
      }
    }
    doc.close();
    this.close(is);
  }

  /**
   * 关闭输入流
   * 
   * @param is
   */
  private void close(InputStream is) {
    if (is != null) {
      try {
        is.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }


  // ==========================================================================
  // ==========================================================================
  // ==========================================================================
  // 写
  public void testSimpleWrite() throws Exception {
    // 新建一个文档
    CustomXWPFDocument doc = new CustomXWPFDocument();
    // 创建一个段落
    XWPFParagraph para = doc.createParagraph();

    // 一个XWPFRun代表具有相同属性的一个区域。
    XWPFRun run = para.createRun();
    run.setBold(true); // 加粗
    run.setText("加粗的内容");
    run = para.createRun();
    run.setColor("FF0000");
    run.setText("红色的字。");
    
    File f = new File("D:\\xx.jpg");
    InputStream in = new FileInputStream(f);   
    
    String picId = doc.addPictureData(in, XWPFDocument.PICTURE_TYPE_PNG);  
    doc.createPicture(picId, doc.getNextPicNameNumber(XWPFDocument.PICTURE_TYPE_PNG), 200, 150);  
    
    OutputStream os = new FileOutputStream("D:\\simpleWrite.docx");
    // 把doc输出到输出流
    doc.write(os);
    doc.close();
    this.close(os);
  }

  /***
   * 写一个表格
   * 
   * @throws Exception
   */
  public void testWriteTable() throws Exception {
    XWPFDocument doc = new XWPFDocument();
    // 创建一个4行5列的表格
    XWPFTable table = doc.createTable(4, 5);
    // 这里增加的列原本初始化创建的那5行在通过getTableCells()方法获取时获取不到，但通过row新增的就可以。
    // table.addNewCol(); //给表格增加一列，变成6列
    // table.createRow(); // 给表格新增一行，变成6行
    List<XWPFTableRow> rows = table.getRows();
    // 表格属性
    CTTblPr tablePr = table.getCTTbl().addNewTblPr();
    // 表格宽度
    CTTblWidth width = tablePr.addNewTblW();
    width.setW(BigInteger.valueOf(8000));
    XWPFTableRow row;
    List<XWPFTableCell> cells;
    XWPFTableCell cell;
    int rowSize = rows.size();
    int cellSize;
    for (int i = 0; i < rowSize; i++) {
      row = rows.get(i);
      // 新增单元格
      row.addNewTableCell();
      // 设置行的高度
      row.setHeight(500);
      // 行属性
      // CTTrPr rowPr = row.getCtRow().addNewTrPr();
      // 这种方式是可以获取到新增的cell的。
      // List<CTTc> list = row.getCtRow().getTcList();
      cells = row.getTableCells();
      cellSize = cells.size();
      for (int j = 0; j < cellSize; j++) {
        cell = cells.get(j);
        if ((i + j) % 2 == 0) {
          // 设置单元格的颜色
          cell.setColor("ff0000"); // 红色
        } else {
          cell.setColor("0000ff"); // 蓝色
        }
        // 单元格属性
        CTTcPr cellPr = cell.getCTTc().addNewTcPr();
        cellPr.addNewVAlign().setVal(STVerticalJc.CENTER);
        if (j == 3) {
          // 设置宽度
          cellPr.addNewTcW().setW(BigInteger.valueOf(3000));
        }
        cell.setText(i + ", " + j);
      }
    }
    // 文件不存在时会自动创建
    OutputStream os = new FileOutputStream("D:\\table.docx");
    // 写入文件
    doc.write(os);
    doc.close();
    this.close(os);
  }

  /**
   * 关闭输出流
   * 
   * @param os
   */
  private void close(OutputStream os) {
    if (os != null) {
      try {
        os.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  // ==========================================================================
  // ==========================================================================
  // ==========================================================================

  // 模板
  public void testTemplateWrite() throws Exception {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("xxDate", "2014-02-28");
    params.put("xxApple", "100");
    params.put("xxTotal", "200");
    String path = System.getProperty("user.dir");
    String filePath = path + "\\src\\tmp.docx";
    InputStream is = new FileInputStream(filePath);
    XWPFDocument doc = new XWPFDocument(is);
    // 替换段落里面的变量
    this.replaceInPara(doc, params);
    // 替换表格里面的变量
    this.replaceInTable(doc, params);
    OutputStream os = new FileOutputStream("D:\\tmpwrite.docx");
    doc.write(os);
    this.close(os);
    this.close(is);
  }

  /**
   * 替换段落里面的变量
   * 
   * @param doc 要替换的文档
   * @param params 参数
   */
  private void replaceInPara(XWPFDocument doc, Map<String, Object> params) {
    Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
    XWPFParagraph para;
    while (iterator.hasNext()) {
      para = iterator.next();
      this.replaceInPara(para, params);
    }
  }

  /**
   * 替换段落里面的变量
   * 
   * @param para 要替换的段落
   * @param params 参数
   */
  private void replaceInPara(XWPFParagraph para, Map<String, Object> params) {
    List<XWPFRun> runs;
    Matcher matcher;
    if (this.matcher(para.getParagraphText()).find()) {

      runs = para.getRuns();
      for (int i = 0; i < runs.size(); i++) {
        XWPFRun run = runs.get(i);
        String runText = run.toString();
        matcher = this.matcher(runText);
        if (matcher.find()) {
          while ((matcher = this.matcher(runText)).find()) {
            runText = matcher.replaceFirst(String.valueOf(params.get(matcher.group(1))));
          }
          // 直接调用XWPFRun的setText()方法设置文本时，在底层会重新创建一个XWPFRun，把文本附加在当前文本后面，
          // 所以我们不能直接设值，需要先删除当前run,然后再自己手动插入一个新的run。
          para.removeRun(i);
          para.insertNewRun(i).setText(runText);
        }
      }
    }
  }

  /**
   * 替换表格里面的变量
   * 
   * @param doc 要替换的文档
   * @param params 参数
   */
  private void replaceInTable(XWPFDocument doc, Map<String, Object> params) {
    Iterator<XWPFTable> iterator = doc.getTablesIterator();
    XWPFTable table;
    List<XWPFTableRow> rows;
    List<XWPFTableCell> cells;
    List<XWPFParagraph> paras;
    while (iterator.hasNext()) {
      table = iterator.next();
      rows = table.getRows();
      for (XWPFTableRow row : rows) {
        cells = row.getTableCells();
        for (XWPFTableCell cell : cells) {
          System.out.println(cell.getText());
          paras = cell.getParagraphs();
          System.out.println(paras.size());
          for (XWPFParagraph para : paras) {
            this.replaceInPara(para, params);
          }
        }
      }
    }
  }

  /**
   * 正则匹配字符串
   * 
   * @param str
   * @return
   */
  private Matcher matcher(String str) {
    Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(str);
    return matcher;
  }

}
