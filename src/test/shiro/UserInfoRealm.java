package test.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class UserInfoRealm extends AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("doGetAuthorizationInfo");
		 Set<String> permissionSet = new HashSet<>();
	        Set<String> roleNameSet = new HashSet<>();
	        roleNameSet.add("1");
	        roleNameSet.add("2");
	        roleNameSet.add("3");
	        permissionSet.add("a");
	        permissionSet.add("b");
	        permissionSet.add("c");
	        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
	        info.addStringPermissions(permissionSet);
	        info.addRoles(roleNameSet);
	        return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		  UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		  System.out.println(token.getUsername());
		  if("lbb".equals(token.getUsername())){
			  return new SimpleAuthenticationInfo(new User("lbb", "123456"), "e10adc3949ba59abbe56e057f20f883e",super.getName());
		  }else {
			  return new SimpleAuthenticationInfo(new User("lbb2", "1234567"), "fcea920f7412b5da7be0cf42b8c93759",super.getName());
		  }
	}
	 @Override
	    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
	        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
	        md5CredentialsMatcher.setHashAlgorithmName("MD5");
//	        md5CredentialsMatcher.setHashIterations(1);
	        super.setCredentialsMatcher(md5CredentialsMatcher);
	    }
	
}
