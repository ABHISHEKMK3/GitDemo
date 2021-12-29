package practise.AppiumFramework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverCommonLib {
	
	public void waitForPageToLoad()
	{
		Initialization.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	}
	
	
	public void waitForXpathPresent(String wbXpath, int WaitTime)
	{
		WebDriverWait wait = new WebDriverWait(Initialization.driver, WaitTime );
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(wbXpath)));
	
	}

}
