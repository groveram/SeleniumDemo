package com.bitwiseglobal.qa;

import java.util.List;
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
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Lasko 751320 Ceramic Tower Heater with Remote Control\r\n");
		Thread.sleep(10000);
		List<WebElement> productNames = driver.findElement(By.id("s-results-list-atf")).findElements(By.tagName("h2"));
		String addedProduct = productNames.get(0).getText();
		productNames.get(0).click();
		Thread.sleep(5000);
		driver.findElement(By.id("add-to-cart-button")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("nav-cart-count")).click();
		Thread.sleep(5000);
		String cartAddedProductValue = driver.findElement(By.xpath("//span[@class='a-size-medium sc-product-title a-text-bold']")).getText();
		System.out.println(cartAddedProductValue);
		System.out.println(addedProduct);
		Assert.assertEquals(cartAddedProductValue.trim(), addedProduct.trim());
		Assert.assertEquals("1", driver.findElement(By.id("nav-cart-count")).getText().trim());
	}

	@After
	public void destroyDriver() {
		driver.quit();
	}
}
