package test.javabase.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyBuilder {
	private InvocationHandler handler;
	
	public ProxyBuilder(InvocationHandler handler) {
		super();
		this.handler = handler;
	}
	public <T> T build(Class<T> t){
		return (T)Proxy.newProxyInstance(t.getClassLoader(), t.getInterfaces(), this.handler);
	}
}
