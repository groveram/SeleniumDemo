package com.bitwiseglobal.qa;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class AmazonHomeTest {
	WebDriver driver;
	
	@Before
	public void initializeDriver() {
		driver = new ChromeDriver();
		driver.get("https://www.amazon.com");
		driver.manage().window().maximize();
	}
	
	@Test
	public void validateSearchFunctionality() throws InterruptedException {
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("heater\r\n");
		Thread.sleep(10000);
		List<WebElement> productNames = driver.findElement(By.id("s-results-list-atf")).findElements(By.tagName("h2"));
		System.out.println("Current URL "+driver.getCurrentUrl());		
		System.out.println("List " + productNames);		
		System.out.println("\n\n\nproducts Avl. "+productNames.size());
		System.out.println("===================================");
		productNames.stream().forEach(e -> System.out.println(e.getText()));
		List<WebElement> elementsWithKeyword = productNames.stream().filter(e -> e.getText().toLowerCase().trim().contains("heater")).collect(Collectors.toList());
		System.out.println("\n\n\n\nElements with keyword \"heater\" = "+elementsWithKeyword.size());
		System.out.println("===================================");
		elementsWithKeyword.stream().forEach(e -> System.out.println(e.getText()));
		Assert.assertTrue(productNames.size()==elementsWithKeyword.size());
	}
	
	@After
	public void destroyDriver() {
	driver.quit();
	}
}
