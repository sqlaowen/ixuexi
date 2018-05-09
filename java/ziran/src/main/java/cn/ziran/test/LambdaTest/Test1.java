package cn.ziran.test.LambdaTest;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wenshiwei on 2016/11/29.
 */
public class Test1 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("zhong", "hua", "ren", "men", "gong", "he", "guo", "wen", "shi", "wei");
        String [] strArr ={"zhong", "hua", "ren", "men", "gong", "he", "guo", "wen", "shi", "wei"};
        Arrays.sort(strArr,(x, y)->{
           return  Integer.compare(x.length(), y.length());
        });
        for (String s : strArr) {
            System.out.println(s);
        }
    }
}
