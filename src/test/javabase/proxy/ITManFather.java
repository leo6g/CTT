package test.javabase.proxy;

public class ITManFather implements Person{

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("ITManFather eat");
		
	}

	@Override
	public void sleep() {
		System.out.println("ITManFather sleep");
	}

	@Override
	public void see() {
		System.out.println("ITManFather see");		
	}
	
}
