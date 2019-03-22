package com.training.Salesforce;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class SalesforceTestcases extends ReusableFunctions {
	WebDriver driver;
	SoftAssert softassert = new SoftAssert();

	@BeforeSuite
	public void initializedriver() {

		initialize("Report.html");
	}

	@BeforeMethod
	public void launchurl() {

		launch("https://login.salesforce.com");

	}

	/*
	 * TC01 Navigate to SFDC. Launch App. Enter User Name. Clear Password field
	 * Click on Log in button
	 */
	@Test(enabled=true)
	public void test1() {
		{
			startreport("TC01-Navigate to SFDC with null password");
			WebElement Un = findElement(By.xpath("//input[@id='username']"), " UserName ");
			entertext(Un, "rajalakshmi.ponkumar@gmail.com");
			WebElement pw = findElement(By.xpath("//input[@id='password']"), "Password");
			pw.clear();
			WebElement logbutton = findElement(By.id("Login"), " Login");
			clickobj(logbutton);
			WebElement act = findElement(By.xpath("//div[@id='error']"), "Error");
			String Actual = act.getText();
			String expected = "Please enter your password.";
			Assert.assertEquals(Actual, expected);
		}
	}
	/*
	 * TC02 Login To SalesForce -2 Launch SFDC application Login to SFDC
	 */

	@Test(enabled=true)
	public void test2() {
		startreport("TC02-Login to Salesforce");
		WebElement Un = findElement(By.xpath("//input[@id='username']"), " UserName");
		entertext(Un, "rajalakshmi.ponkumar@gmail.com");
		WebElement pw = findElement(By.xpath("//input[@id='password']"), "Password");
		entertext(pw, "rajivel123");
		WebElement logbutton = findElement(By.id("Login"), "Login");
		clickobj(logbutton);
		Boolean logo = findElement(By.className("slds-global-header__logo"), "Logo").isDisplayed();
		Assert.assertTrue(logo);
	}

	/*
	 * TC03 Check RemeberMe - 3 Launch SFDC application Login to SFDC with
	 * remember username check box checked Log out of SFDC Check for Username
	 * field
	 */
	@Test(enabled=true)
	public void test3() throws InterruptedException {
		startreport("TC03-Check Remember me");
		WebElement Un = findElement(By.xpath("//input[@id='username']"), " UserName ");
		entertext(Un, "rajalakshmi.ponkumar@gmail.com");
		WebElement pw = findElement(By.xpath("//input[@id='password']"), "Password");
		entertext(pw, "rajivel123");
		WebElement rempwd = findElement(By.id("rememberUn"), " Remember Me");
		selectcheckbox(rempwd);
		WebElement logbutton = findElement(By.id("Login"), " Login");
		clickobj(logbutton);
		Thread.sleep(5000);
		WebElement user = findElement(By.xpath("//span[@class='photoContainer forceSocialPhoto']//img[@title='User']"),"Userprofile");	
		clickobj(user);
		WebElement logout = findElement(By.linkText("Log Out"), "Logout");
		clickobj(logout);
		Thread.sleep(5000);
		WebElement exp = findElement(By.xpath("//span[@id='idcard-identity']"),"expectedvalue");
		String expected =exp.getText(); 
		String Actual = "rajalakshmi.ponkumar@gmail.com";
		softassert.assertEquals(Actual, expected);
	}

	/*
	 * TC04A Test Forgot Password Steps:Launch SFDC application Click on Forgot
	 * password Test Forgot password
	 */
	@Test(enabled=true)
	public void test4A() {
		startreport("TC04A-Forgot Password");
		WebElement forgotpwd = findElement(By.linkText("Forgot Your Password?"), "Forgot Your Password");
		clickobj(forgotpwd);
		WebElement forgotpageun = findElement(By.xpath("//input[@id='un']"), " Enter username");
		entertext(forgotpageun, "rajalakshmi.ponkumar@gmail.com");
		WebElement con = findElement(By.cssSelector("#continue"), "Continue");
		clickobj(con);
	}

	/*
	 * ValidateLoginErrorMessage Launch the application Enter Wrong USerName
	 * Enter wrong PWd Click Login
	 */
	@Test(enabled=true)
	public void test4B() {
		startreport("TC04B-Validate Login Errormessage");
		WebElement Un = findElement(By.xpath("//input[@id='username']"), " UserName ");
		entertext(Un, "123");
		WebElement pw = findElement(By.xpath("//input[@id='password']"), "Password");
		entertext(pw, "12321");
		WebElement logbutton = findElement(By.id("Login"), " Login");
		clickobj(logbutton);
		WebElement act = findElement(By.xpath("//div[@id='error']"), "Error");
		String Actual = act.getText();
		String expected = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		softassert.assertEquals(Actual, expected);
	}

	/*
	 * Select user menu for <username> drop down Launch and Login Check for user
	 * menu for <username> drop down Click on the user menu for <username> drop
	 * down
	 */
	@Test(enabled=true)
	public void test5() throws InterruptedException {
		startreport("TC05-Select usermenu");
		WebElement Un = findElement(By.xpath("//input[@id='username']"), " UserName ");
		entertext(Un, "rajalakshmi.ponkumar@gmail.com");
		WebElement pw = findElement(By.xpath("//input[@id='password']"), "Password");
		entertext(pw, "rajivel123");
		WebElement rempwd = findElement(By.id("rememberUn"), " Remember Me");
		selectcheckbox(rempwd);
		WebElement logbutton = findElement(By.id("Login"), " Login");
		clickobj(logbutton);
		Thread.sleep(5000);
		WebElement user = findElement(By.xpath("//span[@class='photoContainer forceSocialPhoto']//img[@title='User']"),"Userprofile");
		clickobj(user); 
		WebElement switchclassic = findElement(By.linkText("Switch to Salesforce Classic"), "Switch");
		clickobj(switchclassic);

	}

	@AfterMethod
	public void quit() throws InterruptedException {
		quitdriver();
	}

	@AfterSuite
	public void close() {
		reportclose();
	}

}
