
public class Test2 {

	public static void main(String[] args) {
		final Object obj1 = new Object();
		try {
			obj1.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
