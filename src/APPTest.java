import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;

public class APPTest {

	public static void main(String[] args) throws IOException {
		final Object obj = new Object();
		final Object obj1 = new Object();
		
		Thread tt = new Thread() {
			public void run() {
				synchronized (obj) {
					try {
						Thread.sleep(10000);
//						obj.wait(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("tt out synchronized");
				}
				try {
					Thread.sleep(10000);
//					obj.wait(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (obj) {
					System.out.println("get monitor again");
				}
				System.out.println("tt finished");
			}
		};
		System.out.println(tt.getName()+"--"+tt.getState());
		tt.start();
		System.out.println(tt.getName()+"--"+tt.getState());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(tt.getName()+"--"+tt.getState());
		synchronized (obj) {
			try {
				tt.join();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.out.println(tt.getName()+"--"+tt.getState());
			System.out.println("success");
			System.out.println(tt.getName()+"--"+tt.getState());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(tt.getName()+"--"+tt.getState());
		
	/*	
		AtomicLong counter = new AtomicLong();
		ExecutorService pool = Executors.newFixedThreadPool(20);
		pool.execute(new TestThread(counter));
		pool.execute(new TestThread(counter));
		pool.execute(new TestThread(counter));
		pool.shutdownNow();*/
//		for(int i =0;i<1000;i++) {
//			System.out.println(counter.incrementAndGet());
//			System.out.println(counter.get());
//		}
		
		/*System.out.println("hello lee");
		 * 
		System.out.println("hello lee");
		System.out.println("hello lee");
		Map<String,Object> map  = new HashMap<String,Object>();List<Object> list = new ArrayList<Object>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add(4);
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("list", list);
		JSONObject json = (JSONObject)JSONObject.toJSON(map);
		System.out.println(json);*/
	}
}
