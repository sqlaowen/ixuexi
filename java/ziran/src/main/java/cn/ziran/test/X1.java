package cn.ziran.test;

import cn.ziran.xrandom.RandomGUID;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by wenshiwei on 2016/11/24.
 */
public class X1 {

    public static void main(String[] args) throws Exception {

        File file = new File("D:/sql.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 1000000; i++) {
            stringBuffer.append(String.format("INSERT into test(t1, t2, t3, t4) VALUES ('%s', %s, '%s', %s) ;\n",
                    new RandomGUID().toString(), i % 2 == 0 ? 0 : 1,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), String.format("%.2f", new Random().nextFloat())));
            if (0 == (i + 1) % 1000) {
                fileOutputStream.write(stringBuffer.toString().getBytes());
                fileOutputStream.flush();
                stringBuffer = new StringBuffer();
            }
        }
        fileOutputStream.flush();
        fileOutputStream.close();

//        System.out.println( String.format("%.2f",new Random().nextFloat()));

        //System.out.println(String.format("INSERT into test(t1, t2, t3, t4) VALUES ('%s', %s, now(), %s) ;\n", new RandomGUID().toString(), 0, new Random().nextFloat()));

    }

    public void fn1() {
        System.out.println(xx());
        List<String> list =new LinkedList<>();

    }

    protected String xx() {
        return "default";
    }
}
