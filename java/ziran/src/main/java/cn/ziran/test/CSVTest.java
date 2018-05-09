package cn.ziran.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by wenshiwei on 2016/11/24.
 */
public class CSVTest {

    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    writeFile(i + "," + i + "," + i + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 10; i < 20; i++) {
                try {
                    writeFile(i + "," + i + "," + i + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    // 内容格式: x,x,x\n 可以直接写入csv文件
    public static void writeFile(String content) throws IOException {
        OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream("D:/data/xx.csv", true), "UTF-8");
        fw.write(content);
        if(null != fw) {
            fw.close();
        }
    }
}
