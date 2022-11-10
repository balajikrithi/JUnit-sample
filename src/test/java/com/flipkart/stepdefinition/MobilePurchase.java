package com.flipkart.stepdefinition;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MobilePurchase {

	static WebDriver driver;
	
	@BeforeClass
	public static void before() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
//		System.out.println("launch url");
	}
	@AfterClass
	public static void after() {
		System.out.println("close the browser");
	}
	@Before
	public void beforeTest() {
		System.out.println("test started");
	}
	@After
	public void afterTest() {
		System.out.println("test successful");
	}
	@Test
	public void test1() {
		driver.findElement(By.xpath("//button[text()='✕']")).click();
//		System.out.println("login");
	}
	@Test
	public void test2() {
		driver.findElement(By.name("q")).sendKeys("Oneplus mobiles");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//System.out.println("search");
	}
	@Test
	public void test3() throws IOException {
		WebDriverWait w= new WebDriverWait(driver, Duration.ofSeconds(100));
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='_4rR01T']")));
	
		File f= new File("C:\\Users\\DELL\\eclipse-workspace\\SimpleProject\\target\\flipkart.xlsx");
		FileOutputStream F = new FileOutputStream(f);
		XSSFWorkbook w1= new XSSFWorkbook();
		Sheet s= w1.createSheet("Mobiles");
		
		List<WebElement> mobile= driver.findElements(By.xpath("//div[@class='_4rR01T']"));
		for (int i = 0; i < mobile.size(); i++) {
			WebElement mobile1= mobile.get(i);
			String text= mobile1.getText();
			Row r= s.createRow(i);
			Cell c= r.createCell(0);
			c.setCellValue(text);
		}
		{
		w1.write(F);
		}
		
//		System.out.println("list of mobiles");
}
	@Test
	public void test4() throws IOException {
		FileWriter f1= new FileWriter("C:\\Users\\DELL\\eclipse-workspace\\Junit\\target\\notepad.txt");
		String p= driver.getWindowHandle();
		driver.findElement(By.xpath("(//div[@class='_4rR01T'])[3]")).click();
		Set<String> c= driver.getWindowHandles();
		List<String> c1= new ArrayList(c);
		driver.switchTo().window(c1.get(1));
		for (String x : c) {
			if (!x.equals(p)) {
				driver.switchTo().window(x);
			}
		}
		List<String> d= new ArrayList<String>();
		d.addAll(c);
		driver.switchTo().window(d.get(0));
		String text1= driver.findElement(By.xpath("//span[@class='B_NuCI']")).getText();
		f1.write(text1);
		f1.close();
//		System.out.println("window");
	}
	@Test
	public void test5() throws IOException {
		File f= new File("C:\\Users\\DELL\\eclipse-workspace\\Junit\\target\\flipkart.xlsx");
		FileInputStream ip= new FileInputStream(f);
		XSSFWorkbook w2= new XSSFWorkbook(ip);
		
		Sheet s1= w2.getSheet("Mobiles");
		Row row= s1.getRow(3);
		Cell cell= row.getCell(0);
		String S= cell.getStringCellValue();
		System.out.println(S);
		
		FileReader f1= new FileReader("C:\\Users\\DELL\\eclipse-workspace\\Junit\\target\\notepad.txt");
		BufferedReader buf= new BufferedReader(f1);
		String val;
		while ((val= buf.readLine()) !=null) {
			System.out.println(val);
		}
		buf.close();
		
		if (S.equals(val)) {
			System.out.println("matched");
		}
		else {
			System.out.println("not matched");
		}
		boolean b= true;
		Assert.assertTrue(b);
		Assert.assertEquals(S, val);
	}
}
