package com.flipkart.test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Spotify {
	static WebDriver driver;
	static long start;
	@BeforeClass(groups = "common")
	
	public static void launch() {
		System.out.println("BeforeClass");
         WebDriverManager.chromedriver().setup();
         driver=new ChromeDriver();
 		String s = "https://www.irctc.co.in/nget/train-search";
 		driver.get(s);
 		driver.manage().window().maximize();
         driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
	}
	
	@AfterClass(groups = "common")
	public static void Snapshot() throws IOException {
		
		System.out.println("AfterClass");
		DateTimeFormatter patt = DateTimeFormatter.ofPattern("HHmmss");
		LocalDateTime now = LocalDateTime.now();
		String format = patt.format(now);
		System.out.println(format);
		
   TakesScreenshot t = (TakesScreenshot)driver;
   File file = t.getScreenshotAs(OutputType.FILE);
   File f = new File("C:\\Users\\ELCOT\\eclipse-workspaceNew\\MAVEN\\TestNG\\target\\Spotify\\Spotify.png");
   FileUtils.copyFile(file, f);
	}
	
   @BeforeMethod(groups = "common")
   public void startWidth() {
        start = System.currentTimeMillis();
}
   @AfterMethod(groups = "common")
   public void endsWidth() {
	   long ends = System.currentTimeMillis();
	     long t =ends-start;
	     System.out.println("How much time taken :"+t);
   }
   
   
   @Test(priority = -1,groups = "smoke")
   public void homePage() throws InterruptedException  {
	   Thread.sleep(3000);
	   WebElement Al = driver.findElement(By.xpath("//button[text()='OK']"));
		Al.click();
		Thread.sleep(2000);
		WebElement list = driver.findElement(By.xpath("(//input[@role='searchbox'])[1]"));
		list.click();
		
		List<WebElement> list2 = list.findElements(By.xpath("//ul[@role='listbox']"));
		for (WebElement webE : list2) {
			
			Thread.sleep(2000);
			String text = webE.getText();
			System.out.println(text);
			  if (text.contains("CHENNAI EGMORE")) {
					Thread.sleep(2000);
				webE.click();
				System.out.println("clicked");
					
				}	
		}
	  }
   @Test(priority = 0,groups = "Regression")
   public void loginPage() throws InterruptedException {
	   Thread.sleep(3000);
	 
	
	WebElement To = driver.findElement(By.xpath("(//input[@role='searchbox'])[2]"));
	To.click();
	List<WebElement> To2 = To.findElements(By.xpath("//ul[@role='listbox']"));
	for (WebElement webE2 : To2) {
		String text2 = webE2.getText();
		System.out.println(text2);
		Thread.sleep(2000);
		if (text2.contains("COIMBATORE")) {
			Thread.sleep(2000);
			webE2.click();
			
		}
		
	}
	WebElement cli = driver.findElement(By.xpath("(//input[@type='text'])[3]"));
	cli.click();}
	
  
}
