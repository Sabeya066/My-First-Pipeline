package org.tcs.java.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExcelProject {
	
	
//	public static void main(String[] args) throws IOException {
//		File f= new File("C:\\Users\\Kamal Sheriff\\eclipse-workspace\\Excel\\Book.xlsx");
//		FileInputStream fis=new FileInputStream(f);
//		Workbook w=new XSSFWorkbook(fis);
//		org.apache.poi.ss.usermodel.Sheet s=w.getSheet("Sheet1");
//		Row r=s.getRow(2);
//		r.getCell(2);
//		System.out.println("row is:"+r);
//		int rows=s.getPhysicalNumberOfRows();
//		System.out.println(rows);
//		Cell c= r.getCell(1);
//		int getcell=r.getPhysicalNumberOfCells();
//		System.out.println(getcell);
//		System.out.println(c);
//	}
	
	
	
		
		public static void main(String[] args) throws InterruptedException, IOException {

		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Kamal Sheriff\\eclipse-workspace\\fed\\Driver\\chromedriver.exe");
				
ChromeOptions options=new ChromeOptions();

		options.addArguments("--headless"); // CRITICAL for remote pipeline running
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver=new ChromeDriver(options);

		driver.get("https://www.bose.com/home");
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
		try {
		WebElement close= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='close footer-email-modal__modal-close-btn p-0']")));
		//WebElement close=driver.findElement(By.xpath("//button[@class='close footer-email-modal__modal-close-btn p-0']"));
		close.click();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		WebElement search=driver.findElement(By.xpath("(//input[@placeholder='Search'])[2]"));
		
		
		search.sendKeys("Noise Cancelling",Keys.ENTER);
		// Wait up to 10 seconds for the button to become completely clickable online
	try {
		
		WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement resultButton = wait5.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-secondary btn-md btn-block js-all-result-button']")));

		// Now click the safely loaded element
		resultButton.click();
	}
	catch(Exception e) {
		e.printStackTrace();
	}

		//driver.findElement(By.xpath("//button[@class='btn btn-secondary btn-md btn-block js-all-result-button']")).click();
		Thread.sleep(10000);

		//WebElement product=driver.findElement(By.xpath("//img[@data-src='https://assets.bosecreative.com/transform/5dc4d20d-ae18-488a-8054-ebfc6c23ebfe/QCHII27-Blk_PDP_E-Comm_Gallery_1_1500x1120?format=avif&quality=95&io=width:400,height:300,transform:fit&io=width:400,height:300,transform:fit']"));
		//driver.switchTo().frame(product);
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
		WebElement product=driver.findElement(By.xpath("//div[@class='add-to-cart-body'][1]"));
	js.executeScript("arguments[0].click()", product);	


	// 1. Wait until the element is present in the DOM layout
	WebDriverWait wait2=new WebDriverWait(driver, Duration.ofSeconds(15));

	//WebElement add=driver.findElement(By.xpath("//button[@class='add-to-cart btn btn-primary add-to-cart-show js-add-to-cart-btn cart-ipay-group__link']"));
	//WebElement add=wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='add-to-cart btn btn-primary add-to-cart-show js-add-to-cart-btn cart-ipay-group__link']")));
	//add.click();
	try{
	By exactAddToCartSelector = By.cssSelector("button[data-pid='QCUH2-HEADPHONEARN-BLK-WW']");

	//3. Wait for this specific element to load into the DOM structure
	WebElement add = wait2.until(ExpectedConditions.presenceOfElementLocated(exactAddToCartSelector));

	//4. Fire the click event via JavaScript execution context
	js.executeScript("arguments[0].click();", add);

	//WebElement add=driver.findElement(By.xpath("//button[contains(@class='add-to-cart btn btn-primary add-to-cart-show js-add-to-cart-btn cart-ipay-group__link')]"));
	//WebElement add=driver.findElement(By.xpath("//span[text()='Add to Cart'][1]"));

	//js.executeScript("arguments[0].click()", add);

	//Thread.sleep(10000);

	//WebDriverWait wait3=new WebDriverWait(driver, Duration.ofSeconds(15));
	//wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='minicart-link has-product ']")));
		}
		catch(Exception e){
			System.out.println("Click error bypassed. Product already in cart. Proceeding to mini cart...");
		}
	WebElement miniCart=driver.findElement(By.xpath("//button[@class='minicart-link has-product ']"));

	//miniCart.click();

	js.executeScript("arguments[0].click();", miniCart);

	Thread.sleep(5000);
	//Review Cart
	WebElement review=driver.findElement(By.xpath("//a[contains(text(),'Review Cart')]"));
	js.executeScript("arguments[0].click();", review);

	//Checkout
	WebElement Checkout=driver.findElement(By.xpath("//a[@data-validate-basket-url='/on/demandware.store/Sites-Bose_US-Site/en_US/Cart-ValidateBasket']"));
	js.executeScript("arguments[0].click();", Checkout);

	driver.manage().window().maximize();
	js.executeScript("document.body.style.zoom='50%'");

	String projectPath = System.getProperty("user.dir");

	// 2. Create the file reference using a dynamic path
	File Excellocation = new File(projectPath + "/src/test/resources/Book.xlsx");
	FileInputStream fis=new FileInputStream(Excellocation);
	
	Workbook w=new XSSFWorkbook(fis);
	org.apache.poi.ss.usermodel.Sheet s=w.getSheet("Sheet1");
	
	Row r;
	Cell c;
	DataFormatter formatter=new DataFormatter();

	for(int i=1;i<=s.getPhysicalNumberOfRows();i++) {
		
		String Email="";
		String first_name="";
		String last_name="";
		String ph_no="";
		String add_r="";
		String ci_ty="";
		String zi_p="";
		r=s.getRow(i);
		for(int j=0;j<=r.getPhysicalNumberOfCells();j++) {
			r = s.getRow(i);

			// 1. Move the check HERE (Before the inner 'j' loop starts)
			if (r == null) {
			    continue; // Safely skips this entire blank row and jumps to the next row 'i'
			}

if(j==0) {
		
 Email=formatter.formatCellValue(r.getCell(j));
}
else if(j==1){
first_name=formatter.formatCellValue(r.getCell(j));
}
else if(j==2) {
last_name=formatter.formatCellValue(r.getCell(j));
}
else if(j==3) {
ph_no=formatter.formatCellValue(r.getCell(j));
}
else if(j==4) {
add_r=formatter.formatCellValue(r.getCell(j));
}
else if(j==5) {
ci_ty=formatter.formatCellValue(r.getCell(j));
}
else if(j==6) {
 zi_p=formatter.formatCellValue(r.getCell(j));
}
		}
WebElement mail=driver.findElement(By.id("email"));
mail.sendKeys(Email);
WebElement fname=driver.findElement(By.id("shippingFirstNamedefault"));
fname.sendKeys(first_name);
WebElement lname=driver.findElement(By.id("shippingLastNamedefault"));
lname.sendKeys(last_name);
WebElement phno=driver.findElement(By.id("shippingPhoneNumberdefault"));
phno.sendKeys(ph_no);
WebElement addr=driver.findElement(By.id("shippingAddressOnedefault"));
addr.sendKeys(add_r);
//Select s=new Select(addr);
//s.selectByValue("123 Fernandez Ct, Yona GU 96915");


WebElement city=driver.findElement(By.id("shippingAddressCitydefault"));
city.sendKeys(ci_ty);
WebElement state=driver.findElement(By.name("dwfrm_shipping_shippingAddress_addressFields_states_stateCode"));
Select s1=new Select(state);
s1.selectByIndex(2);;
WebElement zip=driver.findElement(By.name("dwfrm_shipping_shippingAddress_addressFields_postalCode"));
zip.sendKeys(zi_p);

WebElement sumit= driver.findElement(By.xpath("//button[contains(text(),'Proceed to payment detail')]"));
//sumit.click();
js.executeScript("arguments[0].click();", sumit);
//Safely close all browser windows and terminate background engine processes
if (driver != null) {
	driver.close();
 driver.quit();
}


	}
	
		
	
	
	

	

		
		}
	}
	
	
	
	


