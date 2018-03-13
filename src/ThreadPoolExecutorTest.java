
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutorTest {
	public static void main(String[] args) {
		while(true){
			try {
				Thread.sleep(2000);
				System.out.println("dsd");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dow();
		}
	}
	public static void dow(){
		ExecutorService fixedThreadPool1 = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; i++) {
			final int index = i;
			fixedThreadPool1.execute(new Runnable() {
				public void run() {
					try {
						System.out.println(index);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
//		fixedThreadPool1.shutdownNow();
//		fixedThreadPool1.shutdown();
	}
}