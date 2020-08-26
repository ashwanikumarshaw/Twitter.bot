package twitter.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class WeblinkOpen {
	static String baseUrl;

	public static void configreader(WebDriver driver) {
		Properties p = new Properties();
		try {
			InputStream inpt = new FileInputStream(
					"src/twitter/util/info.properties");
			p.load(inpt);
			baseUrl = p.getProperty("URL");
			driver.get(baseUrl);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
}
