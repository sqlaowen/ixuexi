package cn.ziran.xfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadFile {

  
  public static void main(String[] args) throws IOException {
    String sp=File.separator;
    String path=System.getProperty("user.dir")+sp+"conf"+sp+"log4j.properties";
    File f=new File(path);
    if(f.exists())
      System.out.println(f);
    InputStream in =new FileInputStream(f);
    BufferedReader br=new BufferedReader(new InputStreamReader(in,"UTF-8"));
    StringBuffer sb=new StringBuffer();
    String tmp="";
    while((tmp=br.readLine())!=null){
      sb.append(tmp+"\r\n");
    }
    if(null!=br){
      br.close();
    }
    if(null!=in){
      in.close();
    }
    System.out.println(sb.toString());
  }
}
