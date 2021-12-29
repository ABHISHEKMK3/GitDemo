package practise.AppiumFramework;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObject.CheckoutPage;
import pageObject.FormPage;
import pageObject.ProductListPage;

public class AmountValidation extends BaseCapability {
	
	
	
	@Test(dataProvider="InputData", dataProviderClass=TestData.class)
	public void totalProductValidation(String input) throws IOException, InterruptedException
	{
	
   startServer();

   Thread.sleep(7000);
   AndroidDriver<AndroidElement> driver = capabilities("GeneralStoreApp");
   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
   
   FormPage fp = new FormPage(driver);
   fp.tapOnCountryDropdownandSelectCountry();
   Utilities util = new Utilities(driver);
   util.scrollToText(input);
   fp.enterYourName();
   fp.selectGenderRadioButton("Female");
   fp.tapOnLetsShopbutton();
   
 
   ProductListPage pl = new ProductListPage(driver);
   pl.scrollTillProductInstance0("Jordan 6 Rings");
   pl.tapOnAddToCartWithIndex(0);
   pl.scrollTillProductInstance0("PG 3");
   pl.tapOnAddToCartWithIndex(0);
   pl.tapOnCartBtn();
   
   CheckoutPage checkout = new CheckoutPage(driver);
   checkout.verifyTotalCartAmount();
	service.stop();

   
   
	}
	
	
	@BeforeTest
	
public void killAllNodes() throws IOException, InterruptedException
{
		Runtime.getRuntime().exec("taskKill /F /M node.exe");
		Thread.sleep(3000);	
	}

}
