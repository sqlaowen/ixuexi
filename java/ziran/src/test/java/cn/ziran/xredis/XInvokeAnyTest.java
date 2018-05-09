package cn.ziran.xredis;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class XInvokeAnyTest {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    Set<Callable<String>> callables = new HashSet<Callable<String>>();

    callables.add(new Callable<String>() {
      @Override
      public String call() throws Exception {
        return "Task 1";
      }
    });
    callables.add(new Callable<String>() {
      @Override
      public String call() throws Exception {
        return "Task 2";
      }
    });
    callables.add(new Callable<String>() {
      @Override
      public String call() throws Exception {
        return "Task 3";
      }
    });
    
    String result = executorService.invokeAny(callables);
    System.out.println("result = " + result);
    
    List<Future<String>> listResult= executorService.invokeAll(callables);
    for(Future<String> f :listResult){
      System.out.println(f.get());
    }
    
    executorService.shutdown();
  }
}
