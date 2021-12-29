package practise.AppiumFramework;

import java.io.IOException;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


public class Ecom_test1 extends BaseCapability
{
	@Test 
	public void totalValue() throws IOException, InterruptedException
	{
		startServer();
		
		//Need to make Global
		String s = "India";
		AndroidDriver<AndroidElement> driver = capabilities("GeneralStoreApp");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		
		
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("android:id/text1")).click();
	
		//if you want to scroll till the text in drop down we can use "AndroidUIAutomator
		
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Italy\"))");
		
	//	driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"+s+\"))").isDisplayed();
		
	//	driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + s + "\").instance(0))")).isDisplayed();
    	driver.findElement(By.xpath("//android.widget.TextView[@text='Italy']")).click();
    	
    	driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	
	
    	driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");	
    	String toastName = driver.findElement(By.xpath("//android.widget.Toast")).getAttribute("name");
    	System.out.println(toastName);
    	Assert.assertEquals("Please enter your name", toastName);
    	
    	driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
    	
    	int count = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
    	System.out.println(count);
    	
    	
    	driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"Air Jordan 4 Retro\").instance(0))"));
    	driver.findElements(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/productAddCart\")).scrollIntoView(new UiSelector().textMatches(\"ADD TO CART\").instance(0))")).get(0).click();
    	
    	driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"Jordan 6 Rings\").instance(0))"));
    	driver.findElements(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/productAddCart\")).scrollIntoView(new UiSelector().textMatches(\"ADD TO CART\").instance(0))")).get(0).click();
    	
    	
    
  //  	scrollTill("Jordan 6 Rings");
    	
  //  	scrollTill("LeBron Soldier 12");
   
    		
    		
        			driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        			
        			Thread.sleep(5000);
        			
        	int productCount =	driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
        	double sum =0;		
        			
        	for(int i =0; i<productCount; i++)
        	{
        		String amount1 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
        		double amount = getAmount(amount1);
        		sum=sum+amount;
        	}
        	
        	System.out.println(sum +"Sum of products");
    		
        	
    		String total  = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        	
        	total = total.substring(1);
        	double totalValue = Double.parseDouble(total);
        	
   System.out.println(totalValue);
   Assert.assertEquals(sum, totalValue);
        		
        
   driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
   service.stop();
   
    		}	
    		

	public static double getAmount(String value)
	{
		value = value.substring(1);
		double amount2value =  Double.parseDouble(value);
		return amount2value;
		
	}
/*	
	public static WebElement scrollTill(String text) throws MalformedURLException
	{
	
		AndroidDriver<AndroidElement> driver = capabilities();
	
			WebElement element1 = driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/productName\")).scrollIntoView(new UiSelector().textMatches(\""+ text +"\").instance(0))"))get(0);
			
		if(element1.equals("Jordan 6 Rings") || element1.equals("LeBron Soldier 12"))
		{
			driver.findElements(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/productAddCart\")).scrollIntoView(new UiSelector().textMatches(\"ADD TO CART\").instance(0))")).get(0).click();

		}
		
		return element1;
	}
    */	
		
    	
    		
    	
    	
    	
		
		
	
	
	

}
