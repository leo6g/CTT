import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceTest {  
  
    static class Task implements Callable<String>{  
        private int i;  
          
        public Task(int i){  
            this.i = i;  
        }  
  
        @Override  
        public String call() throws Exception {  
            Thread.sleep(i*1000);  
            return Thread.currentThread().getName() + "执行完任务：" + i;  
        }     
    }  
      
    public static void main(String[] args) throws InterruptedException, ExecutionException{  
        testExecutorCompletionService();  
    }  
      
    private static void testExecutorCompletionService() throws InterruptedException, ExecutionException{  
        int numThread = 5;  
        ExecutorService executor = Executors.newFixedThreadPool(numThread);  
        CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);  
        List<Future<String>> futures = new ArrayList<Future<String>>();
        for(int i = 1;i<numThread+1;i++ ){  
        	futures.add(completionService.submit(new CompletionServiceTest.Task(i)));  
        }  
//        for(int i =0;i<futures.size();i++){
//        	futures.get(i).cancel(false);
//        }
//        executor.shutdownNow();
//        executor.shutdown();
        for(int i = 1;i<numThread+1;i++ ){  
        	System.out.println("ss");
        	Thread.sleep(10000);
            System.out.println(completionService.poll().get());  
        }
    }  
}