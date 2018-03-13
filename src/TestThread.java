import java.util.concurrent.atomic.AtomicLong;

public class TestThread extends Thread {
	private AtomicLong counter;
	
	public TestThread(AtomicLong counter) {
		super();
		this.counter = counter;
	}
	@Override
	public void run() {
		for(int i = 0;i<1090000;i++) {
			System.out.println(counter.incrementAndGet());
		}
	}

}
