package cn.ziran.test;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by wenshiwei on 2016/12/5.
 */
public class ExecutorServiceTest {

    public static void main(String[] args) throws Exception {

        Map map =new HashMap();
        map.put("k2","v2");
        map.put("k5","v5");
        map.put("k1","v1");

        System.out.println(map);

        ExecutorService exec = Executors.newFixedThreadPool(2);
        List<Callable<Integer>> callableList = new ArrayList<>();
        callableList.add(() -> {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(1000);
            return 1;
        });
        callableList.add(() -> {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(1000);
            return 2;
        });
        callableList.add(() -> {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(1000);
            return 3;
        });
//        exec.shutdown();
        List<Future<Integer>> list =exec.invokeAll(callableList);
        exec.shutdown();
        list.forEach(x-> {
            try {
                System.out.println(x.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

//        XClass x1 = null;//new ExecutorServiceTest().new XClass();
//        List<XClass> xlist = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            x1 = new ExecutorServiceTest().new XClass();
//            x1.setStr(i + "");
//            xlist.add(x1);
//        }
//        xlist.forEach(x -> {
//            System.out.println(x);
//        });
    }

    class XClass{
        private String str;

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return str;
        }
    }
}
