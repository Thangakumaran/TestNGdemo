package com.task;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.internal.reflect.Parameter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipkart {
	@DataProvider(name="Mobile Name")
	public Object[][] getData() {
		return new Object[][] {{"Laptop"}};

	}

	static WebDriver driver;
	static long start;

	@BeforeClass(groups = "common")
	public static void browserLaunch() {
		System.out.println("BeforeClass");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	@AfterClass(groups = "common")
	public static void quit() throws IOException {
		System.out.println("AfterClass");
		//driver.quit();
	}

	@BeforeMethod(groups = "common")
	public void startTime() {
		System.out.println("Before");
		start = System.currentTimeMillis();

	}

	@AfterMethod(groups = "common")
	public void endTime() {
		System.out.println("After");
		long end = System.currentTimeMillis();

		long s = end - start;

		System.out.println("Time taken " + s);
	}
  
	@Test(priority = -1,groups = "smoke",dataProvider = "Mobile Name")
	// @Parameters({"username","password"})
	public void homePage(String name) throws InterruptedException {
	  // WebElement g = driver.findElement(By.xpath("(//input[@type='text'])[2]"));
		//g.sendKeys(pass);
		/////WebElement h = driver.findElement(By.xpath("//input[@type='password']"));
		//h.sendKeys(user);
		///Thread.sleep(2000);
	   try {
			
			WebElement a = driver.findElement(By.xpath("//button[text()='???']"));
			a.isDisplayed();
			a.click();
		} catch (Exception e) {
			System.out.println("Login popup is closed");
		}
		Thread.sleep(2000);
		WebElement b = driver.findElement(By.xpath("//input[@type='text']"));
		b.sendKeys(name, Keys.ENTER);
	}

	static String text;

	@Test(priority = 0,groups = "smoke")
	public void scrollDown() throws InterruptedException {
		Thread.sleep(3000);
		WebElement b = driver.findElement(By.xpath("//div[contains(text(),'ASUS Core i3 10th Gen - (4 GB ')]"));
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("arguments[0].scrollIntoView(true)", b);
		j.executeScript("arguments[0].click()", b);
		text = b.getText();
		System.out.println(text);
	}

	@Test(priority = 1)
	public void windowHandle() throws InterruptedException {

		String c = driver.getWindowHandle();
		Set<String> d = driver.getWindowHandles();
		for (String e : d) {
			if (!c.equals(e)) {
				driver.switchTo().window(e);
			}

		}
	}

	@Test(priority = 2)
	public void addToCart() throws InterruptedException {
		Thread.sleep(3000);
		WebElement i = driver.findElement(By.xpath("//button[text()='ADD TO CART']"));
		i.click();

	}
	@Test(priority = 3,invocationCount = 2,groups = "UAT")
	public void screenshot() throws IOException {
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("Hmm");
		LocalDateTime now = LocalDateTime.now();
		String format = ofPattern.format(now);
		System.out.println(format);

		TakesScreenshot t = (TakesScreenshot) driver;
		File screenshotAs = t.getScreenshotAs(OutputType.FILE);
		File f = new File("C:\\Users\\ELCOT\\eclipse-workspaceNew\\MAVEN\\TestNG\\target\\Snapshot" + format + ".png");
		FileUtils.copyFile(screenshotAs, f);
	}

	static String text2;

	@Test(priority = 4,enabled = true)
	public void assertionE() {
		WebElement g = driver.findElement(By.xpath("//span[contains(text(),'RedmiBook 15 e-L')]"));
		String text2 = g.getText();
		System.out.println(text2);
		SoftAssert s = new SoftAssert();
		s.assertTrue(text2.equals("Mi Laptop"));
		//s.assertEquals(true, false);
		Assert.assertTrue(text.equals(g));
	}

	@Test(priority = 5,enabled = false)
	public void assertionF() {
		WebElement l = driver.findElement(By.xpath("//a[contains(text(),'RedmiBook 15 e-Learning ')]"));
		String text3 = l.getText();
		System.out.println(text3);
		Assert.assertTrue(text2.equals(text3));

	}

}
