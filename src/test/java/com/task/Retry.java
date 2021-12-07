package com.task;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	
	int min = 0;
	int max =2;
	public boolean retry(ITestResult result) {
		
		if(min<max) {
			min++;
			System.out.println("Try to get failure :"+result.getTestName());
			return true;
		}
		
		
		return false;
	}

}
