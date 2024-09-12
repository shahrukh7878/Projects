package Test.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Test {
	
	
	public static WebDriver driver;

	public static void main(String[] args) {
		
		System.setProperty("webdriver.edge.driver", "C://Users//ShahrukhAata_l4//New//Test//driver//edgedriver_win64//msedgedriver.exe");
	 driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		//driver.get(prop.getProperty("url"));
		driver.get("https://outlook.office.com/mail/");

	}

}
