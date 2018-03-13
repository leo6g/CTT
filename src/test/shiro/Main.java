package test.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class Main {

	public static void main(String[] args) {
		Factory<SecurityManager> factory =
				new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager manager = factory.getInstance();
		SecurityUtils.setSecurityManager(manager);
		Subject currentUser = SecurityUtils.getSubject();
		if ( !currentUser.isAuthenticated() ) {
		    UsernamePasswordToken token = new UsernamePasswordToken("lbb2", "1234567");
		    token.setRememberMe(true);
		    currentUser.login(token);
		    System.out.println("登录成功");
		}
		if(currentUser.hasRole("1")) {
			System.out.println("1");
		}
		if(currentUser.hasRole("4")) {
			System.out.println("4");
		}
		if(currentUser.isPermitted("a")) {
			System.out.println("a");
		}
		if(currentUser.isPermitted("d")) {
			System.out.println("d");
		}
		if(currentUser.isPermitted("a:dsdsdsdsd")) {
			System.out.println("a:b");
		}
	}

}
