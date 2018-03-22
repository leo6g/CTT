package test.javabase.proxy;

import java.lang.reflect.Method;

public class HumanLogHandler<T> extends SurrondInvokationHandler<T> {
	
	public HumanLogHandler(T target) {
		super.target=target;
	}
	
	@Override
	boolean doBefore(T proxy, Method method, Object[] args) throws Throwable{
		System.out.println("before invoke");
		if(method.getName().equals("eat")) {
			System.out.println("nothing to eat");
			getLastMethod().invoke(proxy, args);
			return false;
		}
		return true;
	}

	@Override
	void doAfter(T proxy, Method method, Object[] args, Object returnValue) throws Throwable{
		System.out.println("after invoke");
	}
	
}
