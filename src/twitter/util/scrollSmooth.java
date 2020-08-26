package twitter.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class scrollSmooth {
	
	public static void scrollSmoothDown(WebDriver driver){
	    for(int i=0;i<6000;i++) {
	        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1)", "");
	    }
	}
	public static void scrollSmoothUp(WebDriver driver){
	    for(int i=0;i<3000;i++) {
	        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-1)", "");
	    }
	}

}
