package test.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Parsing The Configuration File
 * @author lbb
 */
public final class PropertiesUtil {
	private static final String BUNDLE_NAME = "config/application";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);
	private static final String ENCRYPTKEY = PropertiesUtil.getString("encryptKey");
	private PropertiesUtil() {
	}
	/**
	 * 根据key获取值，key不存在则返回null
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return null;
		}
	}

	/**
	 * 根据key获取�?
	 * 
	 * @param key
	 * @return
	 */
	public static int getInt(String key) {
		return Integer.parseInt(getString(key));
	}
	
	/**
	 * 根据key获取解密后的
	 * @return
	 */
	public static String getDecryString(String key){
		String str = getString(key);
		String decryStr = "";
		try {
			decryStr = new String(EncryptionUtil.decode(
					EncryptionUtil.hex2byte(str), "ccserver".getBytes()));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryStr;
	}
	/*public static void main(String[] args) {
//		String str = getDecryString("REDIS_ADDR_CFG");
//		System.out.println(str);
		try {
			byte[] be = EncryptionUtil.encode("we".getBytes() , "ccserver".getBytes());
			String hex = EncryptionUtil.byte2hex(be);
			System.out.println(hex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}*/
}