package test.javabase.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public abstract class SurrondInvokationHandler<T> implements InvocationHandler {
	volatile Method lastMethod;
	T target;
	abstract boolean doBefore(T proxy,Method method,Object[] args) throws Throwable;
	
	abstract void doAfter(T proxy,Method method,Object[] args,Object returnValue) throws Throwable;
	Method getLastMethod(){
		return this.lastMethod;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		T t = (T)proxy;
		if(!doBefore(t, method, args)) return null;
		Object returnValue = method.invoke(target, args);
		this.lastMethod = method;
		doAfter(t, method, args, returnValue);
		return returnValue;
	}
	
}
