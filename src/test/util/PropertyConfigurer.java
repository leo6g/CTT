package test.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Decrypt the password to the properties file
 */
public class PropertyConfigurer extends
		org.springframework.beans.factory.config.PropertyPlaceholderConfigurer {

	protected void loadProperties(Properties props) throws IOException {
		super.loadProperties(props);
		String encryptKey = PropertiesUtil.getString("encryptKey");
		try {
			String password = props.getProperty("mypassword");
			String decryPassword = new String(EncryptionUtil.decode(
					EncryptionUtil.hex2byte(password), encryptKey.getBytes()));
			props.setProperty("mypassword", decryPassword);
		} catch (Exception e) {
			logger.error("decode password in properties error!", e);
		}
	}
}