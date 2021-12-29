package pageObject;

import java.util.List;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import practise.AppiumFramework.Initialization;


public class ProductListPage extends Initialization{
	
	
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	public WebElement cartBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='ADD TO CART']")
	public List<WebElement> AddToCart;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productName")
	public WebElement productList;
	

	
	public ProductListPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
	}
	
	public void tapOnAddToCartWithIndex(int i)
	{
		AddToCart.get(i).click();
		Reporter.log("Tapped on zero index of", true);
	}
	
	public void tapOnCartBtn()
	{
		cartBtn.click();
		Reporter.log("Clicked on CartButtonf", true);
	}
	
	public void scrollTillProductInstance0(String text)
	{
		try
		{
			driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\""+text+"\").instance(0))")).isDisplayed();
			Reporter.log("Scrilled Till :" + text, true );
		}
		catch(Exception e) {
			driver.findElementByXPath("//android.widget.TextView[@text='"+text+"']");
			Reporter.log(text+"is displayed", true );
		}
	    

			
		
	}
	
	
	
}
