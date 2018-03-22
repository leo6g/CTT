package test.javabase.proxy;

public class TestProxy {

	public static void main(String[] args) {
		Person x = new ITMan("xiaoming");
		Person p = new ITManFather();
		Person person = new ProxyBuilder(new HumanLogHandler(x)).build(x.getClass());
		person.sleep();
		person.eat();
		person.see();
	}

}
