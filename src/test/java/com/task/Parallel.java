package com.task;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Parallel {

	//@Test(retryAnalyzer = Retry.class)
	@Test
	public void chrome1() {

	
	 WebDriverManager.chromedriver().setup();
  WebDriver   driver=new ChromeDriver();
		String s = "https://www.flipkart.com/";
		driver.get(s);
		driver.manage().window().maximize();
     driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
     
     WebElement a = driver.findElement(By.xpath("//button[text()='✕']"));
		a.isDisplayed();
		a.click();
     
}
	@Test
public void chrome2() {

	 WebDriverManager.chromedriver().setup();
   WebDriver  driver=new ChromeDriver();
		String s = "https://www.irctc.co.in/nget/train-search";
		driver.get(s);
		driver.manage().window().maximize();
     driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
	
}



}
	
