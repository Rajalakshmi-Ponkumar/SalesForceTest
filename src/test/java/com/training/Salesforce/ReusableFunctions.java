package com.training.Salesforce;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReusableFunctions {

	static WebDriver driver;
	static ExtentReports report;
	static ExtentTest Logger;
	
	/* Name : Initialize Description - Initialize the driver and extent report
	 * Arguments reportfilename -filename to be created for the report
	 * Created by : Rajalakshmi
	 * Created Date :21-03-3019 
	 * Last modified date :21-03-2019
	 */
	public static void initialize(String reportfilename) {
		System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
		
		String path = "C:\\TrainingJAN19\\ExtentReport\\" + reportfilename;
		report = new ExtentReports(path);
	}
	/* Name : Launch Description - Launching the driver
	 * Arguments url-URL to be launched,text-text for starting the report
	 * Created by : Rajalakshmi
	 * Created Date :21-03-3019 
	 * Last modified date :21-03-2019
	 */
	public static void launch(String url) {
		driver=new ChromeDriver();
		driver.get(url);		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	public static void startreport(String text)
	{
		Logger = report.startTest(text);
	}

	/* Name : EnterText Description - Enter the text value in the object field
	 * Arguments Object-Name of the object value-value of the object 
	 * Created by : Rajalakshmi
	 * Created Date :21-03-3019 
	 * Last modified date :21-03-2019
	 */
	public static void entertext(WebElement element, String value) {
		if (element == null)
			return;
		if (element.isDisplayed()) {
			element.sendKeys(value);
			Logger.log(LogStatus.INFO, "Pass");
		} else {
			Logger.log(LogStatus.INFO, "Fail");
		}

	}
	/* Name : clickobj Description - Click the object
	 * Arguments element-Element to be clicked
	 * Created by : Rajalakshmi
	 * Created Date :21-03-3019 
	 * Last modified date :21-03-2019
	 */
	public static void clickobj(WebElement element) {
		if(element.isDisplayed()){
			if (element.isEnabled()) {
				element.click();
			
			Logger.log(LogStatus.INFO, "Pass");
			}
		}
		 else {
			Logger.log(LogStatus.INFO, "Fail");
		}
		
	}

	/* Name : selectcheckbox Description - Select the checkbox
	 * Arguments element-element to be checked
	 * Created by : Rajalakshmi
	 * Created Date :21-03-3019 
	 * Last modified date :21-03-2019
	 */ 
	public static void selectcheckbox(WebElement element) {
		if (element == null)
			return;
		if (element.isDisplayed()) {
			if (element.isEnabled()) {
				element.click();
			}

			Logger.log(LogStatus.INFO, "Pass");
		}

		else {
			Logger.log(LogStatus.INFO, "Fail");
		}
	}
     
	/* Name : findElement Description - Finding the element 
	 * Arguments Location-Location where the element to be checked,objname-Name of the object
	 * Created by : Rajalakshmi
	 * Created Date :21-03-3019 
	 * Last modified date :21-03-2019
	 */
	public static WebElement findElement(By location, String objname) {
		WebElement obj = null;
		try {
			obj = driver.findElement(location);
			System.out.println("Pass");
		} catch (NoSuchElementException ex) {
			System.out.println("Fail");
		}
		return obj;
	}
     
	/* Name : MouseOverAction Description - Perform mouseover action on the element 
	 * Arguments element -Element where the action to be performed
	 * Created by : Rajalakshmi
	 * Created Date :21-03-3019 
	 * Last modified date :21-03-2019
	 */
	public static void mouseover(WebElement element) {
		Actions actionobj = new Actions(driver);
		if (element.isDisplayed()) {
			actionobj.moveToElement(element).build().perform();
			Logger.log(LogStatus.INFO, "Pass");
		} else {
			Logger.log(LogStatus.INFO, "Fail");
		}

	}
    
	/* Name : DropdownSelectByvalue Description -Select the option from the dropdownmenu by value
	 * Arguments element -element where the action to perform,selvaue-value to be selected
	 * Created by : Rajalakshmi
	 * Created Date :21-03-3019 
	 * Last modified date :21-03-2019
	 */
	public static void dropdownSelectByValue(WebElement element, String selvalue) {
		if (element.isDisplayed()) {
			Select selobj = new Select(element);
			selobj.selectByValue(selvalue);
			Logger.log(LogStatus.INFO, "Pass");
		} else {
			Logger.log(LogStatus.INFO, "Fail");
		}

	}
    
	/* Name : DropdownSelectByIndex Description -Select the option from the dropdownmenu by index
	 * Arguments element -element where the action to perform,selvalue-index to be selected
	 * Created by : Rajalakshmi
	 * Created Date :21-03-3019 
	 * Last modified date :21-03-2019
	 */
	public static void dropdownSelectByIndex(WebElement element, Integer selvalue) {
		if (element.isDisplayed()) {
			Select selobj = new Select(element);
			selobj.selectByIndex(selvalue);
			Logger.log(LogStatus.INFO, "Pass");
		} else {
			Logger.log(LogStatus.INFO, "Fail");
		}

	}
	
	/* Name : DropdownSelectByVisibleTextDescription -Select the option from the dropdownmenu by visibletext
	 * Arguments element -element where the action to perform,selvalue-visibletext to be selected
	 * Created by : Rajalakshmi
	 * Created Date :21-03-3019 
	 * Last modified date :21-03-2019
	 */
	public static void dropdownSelectByVisibleText(WebElement element, String selvalue) {
		if (element.isDisplayed()) {
			Select selobj = new Select(element);
			selobj.selectByVisibleText(selvalue);
			Logger.log(LogStatus.INFO, "Pass");
		} else {
			Logger.log(LogStatus.INFO, "Fail");
		}

	}
	
	public void quitdriver() throws InterruptedException
	{	
	Thread.sleep(5000);
	driver.quit();
	}
  public void  reportclose()
  {
	  report.endTest(Logger);
		report.flush();
  }
}
