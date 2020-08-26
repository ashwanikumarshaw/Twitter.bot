package twitter.main;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import twitter.util.DriverSetup;
import twitter.util.Login;
import twitter.util.scrollSmooth;
import twitter.util.WeblinkOpen;

public class MainRunner {
	public static WebDriver driver;
	public static int flw=0,flwing=0;

	public static ArrayList<String> following = new ArrayList<String>();
	public static ArrayList<String> follower = new ArrayList<String>();
	
	public static int RunOnly() {

		System.out.println("");
		System.out.println("***********************BROWSER**********************");
		System.out.println("");
		System.out.println("****************************************************");
		System.out.println("");
		driver = DriverSetup.configreader();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		System.out.println("");
		System.out.println("**********************TWITTER********************");
		System.out.println("");
		System.out.println("****************************************************");
		System.out.println("");
		
		WeblinkOpen.configreader(driver);
		String titel =driver.getTitle();
		if("twitter".contains(titel)) {
			System.out.println("Link Opened");
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			System.out.println("Link failed");
			return 0;
		}
		

		System.out.println("");
		System.out.println("**********************LOGIN************************");
		System.out.println("");
		Login.configreader(driver);
		//if(driver.getTitle().contains(""))
		driver.findElement(By.xpath("//*[@id='react-root']/div/div/div[2]/header/div/div/div/div[1]/div[2]/nav/a[7]/div/div")).click();
		System.out.println("Clicked Profile");
		
		
		flw=Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[5]/div[2]/a/span[1]/span")).getText());
		flwing=Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[5]/div[1]/a/span[1]/span")).getText());
		driver.findElement(By.xpath("//*[@id='react-root']/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[5]/div[2]/a/span[2]/span")).click();

		System.out.println("");
		System.out.println("****************************************************");
		System.out.println("");
		
		System.out.println("Clicked followers");
		
		System.out.println("Number of followers :: "+flw);
		int i=1;

		System.out.println("");
		System.out.println("****************************************************");
		System.out.println("");
		
		scrollSmooth.scrollSmoothDown(driver);
		scrollSmooth.scrollSmoothUp(driver);

		while(i<=flw) {

		String followerxpath="//*[@id='react-root']/div/div/div[2]/main/div/div/div/div/div/div[2]/section/div/div/div["+i+"]/div/div/div/div[2]/div[1]/div[1]/a/div/div[1]/div[1]/span/span";
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		boolean exists = driver.findElements( By.xpath(followerxpath)).size() != 0;
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		if(exists) {
		
	    String Name=driver.findElement(By.xpath(followerxpath)).getText();
		    
	    follower.add(Name);
	    
	    double per = (double)i/flw;
	    System.out.println(" covered : "+per*100+"%");
		}
	    i++;
		}
		

		System.out.println("");
		System.out.println("****************************************************");
		System.out.println("");
		
		System.out.println("Your follower: ");
		for(String f:follower)
			System.out.println(f);
		
		

		System.out.println("");
		System.out.println("****************************************************");
		System.out.println("");
		
		driver.findElement(By.xpath("//*[@id='react-root']/div/div/div[2]/main/div/div/div/div/div/div[1]/div[2]/nav/div[2]/div[2]/a/div/span")).click();
		System.out.println("Clicked following");
		System.out.println("Number of following :: "+flwing);
		i=1;

		scrollSmooth.scrollSmoothDown(driver);
		scrollSmooth.scrollSmoothUp(driver);
		while(i<=flwing) {
		String followingxpath="//*[@id='react-root']/div/div/div[2]/main/div/div/div/div/div/div[2]/section/div/div/div["+i+"]/div/div/div/div[2]/div[1]/div[1]/a/div/div[1]/div[1]/span/span";
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		boolean exists = driver.findElements( By.xpath(followingxpath)).size() != 0;
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		if(exists) {
	    String Name=driver.findElement(By.xpath(followingxpath)).getText();
	    boolean ans = follower.contains(Name);

	    //System.out.println(ans);
	    if(ans)
	    	following.add(Name);
	    else {
	    	System.out.println("Unfollowing "+Name+" ...");
	    	String unfolxpat="//*[@id='react-root']/div/div/div[2]/main/div/div/div/div/div/div[2]/section/div/div/div["+i+"]/div/div/div/div[2]/div[1]/div[2]/div";
	    	driver.findElement(By.xpath(unfolxpat)).click();
	    	String conf="//*[@id=\"layers\"]/div[2]/div/div/div/div/div/div[2]/div[2]/div[3]/div[2]";
	    	driver.findElement(By.xpath(conf)).click();
	    }

	    double per = (double)i/flwing;
	    System.out.println(" covered : "+per*100+"%");
		}
		
	    i++;
	
		}
		
		System.out.println("");
		System.out.println("****************************************************");
		System.out.println("");

		System.out.println("Now you follow only: ");
		for(String f:following)
			System.out.println(f);
		

		System.out.println("");
		System.out.println("************************LOGOUT*********************");
		System.out.println("");
		String logout="//*[@id=\"layers\"]/div[2]/div/div/div/div/div/div[2]/div[2]/div[3]/div[2]";
		driver.navigate().to("http://twitter.com/logout");
		driver.findElement(By.xpath(logout)).click();
		System.out.println("");
		System.out.println("****************************************************");
		System.out.println("");
		driver.quit();
		return 1;

	}
	
	public static void main(String[] args) {
		RunOnly();
	}

}
