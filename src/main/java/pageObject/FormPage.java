package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import practise.AppiumFramework.BaseCapability;


public class FormPage extends BaseCapability {
	

	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private  WebElement nameField;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']")
	private  WebElement maleRadioButton;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
	private  WebElement femaleRadioButton;

	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private  WebElement letsShopButton;
	
	@AndroidFindBy(id="android:id/text1")
	private  WebElement countryDropdown;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Afghanistan']")
	public WebElement selectCountryFromDropdown;
	
	
	//initialize our driver
	public FormPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void tapOnCountryDropdownandSelectCountry()
	{
		countryDropdown.click();
		Reporter.log("Tapped on Argentina country Dowpdown", true);	
	}
	
	public void enterYourName()
	{
		nameField.sendKeys("Testing products");
		Reporter.log("Entered Name Testing products", true);
	}
	
	public void selectGenderRadioButton(String text)
	{
		try 
		{
			if(text.equals("Male"))
			{
				maleRadioButton.click();
				Reporter.log("Selected Male Radio button", true);
			}
			else
			{
				femaleRadioButton.click();
				Reporter.log("Selected female Radio button", true);
			}
		}
		catch(Exception e)
		{
		   Assert.fail("Didnt select any Radio button");
		   Reporter.log("Not Selected any Radio button", true);
		}
		
	}
	
	public void tapOnLetsShopbutton()
	{
		letsShopButton.click();
		Reporter.log("Entered Name Testing products", true);
	}
	
	
	
}
