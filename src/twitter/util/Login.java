package twitter.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Login {
	static String Id,Password;

	public static void configreader(WebDriver driver) {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@id='react-root']/div/div/div/main/div/div/div/div[1]/div/a[2]/div")).click();
		Properties p = new Properties();
		try {
			InputStream inpt = new FileInputStream(
					"src/twitter/util/info.properties");
			p.load(inpt);
			Id = p.getProperty("Id");
			Password = p.getProperty("Pass");
			System.out.println(Id +"    **********");

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						
			driver.findElement(By.xpath("//*[@id='react-root']/div/div/div[2]/main/div/div/div[1]/form/div/div[1]/label/div/div[2]/div/input")).sendKeys(Id);
			driver.findElement(By.xpath("//*[@id='react-root']/div/div/div[2]/main/div/div/div[1]/form/div/div[2]/label/div/div[2]/div/input")).sendKeys(Password);
			driver.findElement(By.xpath("//*[@id='react-root']/div/div/div[2]/main/div/div/div[1]/form/div/div[3]/div/div")).click();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

}
