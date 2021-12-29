package pageObject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import practise.AppiumFramework.BaseCapability;

public class CheckoutPage extends BaseCapability {
	
	AndroidDriver<AndroidElement> driver;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	public WebElement totalAmount;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productName")
	public WebElement productNamelist;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	public WebElement productPricelist;
	
	public CheckoutPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
	}
	
	
	public void verifyTotalCartAmount()
	{
	  double sum = 0;
	  int  count = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
	  System.out.println(count);
	  for(int i=0;i<count;i++)
	  {
		  
		String stringcartAmount =  driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
		double amount = getConvertAmount(stringcartAmount);
		sum=sum+amount;
		System.out.println(sum);
		  
	  }
	    String stringActualAmount =  driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		 double finalamount = getConvertAmount(stringActualAmount);
		 Assert.assertEquals(sum, finalamount);
	  
	}
	 public static double getConvertAmount(String value)
	 {
		 value = value.substring(1);
		 System.out.println(value);
		 double convertValue = Double.parseDouble(value);
		 return convertValue;
	 }
	 
	
			  
		
	}
	
	//com.androidsample.generalstore:id/totalAmountLbl


