package test.javabase.proxy;

public class ITMan extends ITManFather implements Person {
	private String name;
	
	public ITMan(String name) {
		super();
		this.name = name;
	}

	@Override
	public void eat() {
		System.out.println(name+"ITMan eat");
	}

	@Override
	public void sleep() {
		System.out.println(name+"ITMan sleep");
	}

	@Override
	public void see() {
		System.out.println(name+"ITMan see");
		
	}

}
