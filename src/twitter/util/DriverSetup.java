package twitter.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
public class DriverSetup {
	static WebDriver driver;
	static String baseUrl;
	public static WebDriver configreader()
	{	String chromeDriverpath;
		String firefoxDriverpath;
		
		
		Properties p=new Properties();
		try {
			InputStream inpt=new FileInputStream("src/twitter/util/info.properties");
			p.load(inpt);
			if((p.getProperty("browser")).equalsIgnoreCase("chrome")) {
				chromeDriverpath=p.getProperty("chromeDriver");
				System.setProperty("webdriver.chrome.driver", chromeDriverpath);
				WebDriver driver=new ChromeDriver();
				driver.manage().window().maximize();
				
				return driver;
			}
			else if((p.getProperty("browser")).equalsIgnoreCase("firefox"))
			{
				firefoxDriverpath=p.getProperty("firefoxDriver");
				System.setProperty("webdriver.gecko.driver", firefoxDriverpath);
				WebDriver driver=new FirefoxDriver();
				driver.manage().window().maximize();
				
				
				return driver;
			}
			else
			{
				System.out.println("Un-supported broswer found");
				System.exit(0);
				return null;
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return driver;
	
	}

}
