package practise.AppiumFramework;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;



public class BaseCapability {
	
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;
	public static String Appium_Path = "C:\\Users\\Abhi\\AppData\\Local\\Programs\\Appium Server GUI\\resources\\app\\node_modules\\appium\\build\\lib\\main.js";
	
	public static String NodePath = "C:\\Program Files\\nodejs\\node.exe";
	
	  static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	  
	
	
	/*
	
	public AppiumDriverLocalService startServer()
	{
	boolean flag =	checkIfServiceIsrunning(4723); //if already running it will return true.
	System.out.println(flag);
	if(!flag) //!flag (Not(true)) = false will skip
	{
		
		System.out.println("Start Appium Server");
		service = AppiumDriverLocalService.buildDefaultService();
	
		service.start();
		
		
	//	if (service == null || !service.isRunning()) {
	//	throw new ExceptionInInitializerError("An appium server node is not started!");
	
	}
		
		return service;
	}
	*/
	
	public static boolean checkIfServiceIsrunning(int port)
	{
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		
		try
		{
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		}
		catch(IOException e)	
		{
			//if control comes here then it means the port is in use.
			isServerRunning = true;
			System.out.println("Port is in Use");
		}
		finally
		{
			serverSocket = null;
		}
		
		return isServerRunning;
	}
	
	
	public static void takeScreenShot(String s) throws IOException
	{
		File srcFile= driver.getScreenshotAs(OutputType.FILE);
		System.out.println(System.getProperty("user.dir"));
		FileUtils.copyFile(srcFile, new File("D:\\Auto-Projects\\eclipse-workspace\\AppiumFramework\\screenShots\\" +s+".png"));
		
		
	}

	public static void startEmulator() throws InterruptedException, IOException
	{
		
		System.out.println(System.getProperty("user.dir"));
//	
		Runtime.getRuntime().exec("D:\\Auto-Projects\\eclipse-workspace\\AppiumFramework\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(8000);
		
	}
	
	public static AppiumDriverLocalService  startServer()
	{
		boolean flag =	checkIfServiceIsrunning(4723); //if already running it will return true.
		System.out.println(flag);
		if(!flag) //!flag (Not(true)) = false will skip
		{
		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File(NodePath))
				.withAppiumJS(new File(Appium_Path))
				.withIPAddress("127.0.0.1")
				.usingPort(4723)
				.withLogFile(new File("D:\\AUTOMATION\\LogFile\\AppiumLog.txt")));
		
		System.out.println("Starting the Appium Server ...."+"\n"+sdf.format(new Date()) + "\n----------------");
		service.start();
		
		}
		return service;
	}
	
	public static void stopServer()
	{
		if(service.isRunning())
		{
			service.stop();
			System.out.println("Stopping Appium Server ----"+"\n" + sdf.format(new Date()));
		}
	}


	public static AndroidDriver<AndroidElement> capabilities(String apk) throws IOException, InterruptedException
	{
		
		
		// System.getProperty("user.dir"); will get the current path of current directory using java method.
		
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\practise\\AppiumFramework\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
		//String device = (String) prop.get("device");
		
		//Running any device from command prompt = mvn test -DdeviceName=Android Device
		   String device = System.getProperty("deviceName");
		
		//How to get project path
		
		if(device.contains("emulator"))
		{
			startEmulator();
			Thread.sleep(5000);
		}
		
		File appDir = new File("src");
		File app = new File(appDir, (String) prop.get(apk));

		
		
		DesiredCapabilities caps = new DesiredCapabilities();
		
		
		caps.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		
		
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20);
		
	driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		
		return driver;
	}

}
