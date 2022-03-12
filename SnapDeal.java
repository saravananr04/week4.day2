package week4.day2;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args)  throws InterruptedException, IOException{
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.snapdeal.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement findElement = driver.findElement(By.xpath("//span[@class='catText']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(findElement).click().perform();
		WebElement element = driver.findElement(By.xpath("//span[@class='linkTest']"));
		builder.moveToElement(element).click().perform();
		String items = driver.findElement(By.xpath("//span[@class='category-name category-count']")).getText();
		System.out.println("Total number of Sports Shoes : " + items);
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		driver.findElement(By.className("sort-selected")).click();
		WebElement element2 = driver.findElement(By.xpath("(//li[@data-index='1'])[2]"));
		builder.moveToElement(element2).click().perform();
		WebElement startingRange = driver.findElement(By.xpath("//input[@class='input-filter']"));
		startingRange.clear();
		builder.click(startingRange).sendKeys("900").perform();
		WebElement endRange = driver.findElement(By.xpath("(//input[@class='input-filter'])[2]"));
		endRange.clear();
		builder.click(endRange).sendKeys("1200").perform();
		driver.findElement(By.xpath("//div[contains(@class,'price-go-arrow btn')]")).click();
		Thread.sleep(2000);
		WebElement color = driver.findElement(By.xpath("//label[@for='Color_s-Navy']"));
		builder.click(color).perform();
		Thread.sleep(2000);
		WebElement firstElement = driver.findElement(By.xpath("//p[@title='Red Tape Walking  Navy Training Shoes']"));
		builder.moveToElement(firstElement).perform();
		WebElement quickView = driver.findElement(By.xpath("//div[contains(@class,'center quick-view-bar')]"));
		builder.click(quickView).perform();
		String firstWindow = driver.getWindowHandle();
		System.out.println(firstWindow);
		WebElement price = driver
				.findElement(By.xpath("//span[@class = 'payBlkBig']"));
		System.out.println("Price of the shoe is Rs." + price.getText());
		WebElement offerPercentage = driver.findElement(By.xpath("//span[@class ='percent-desc ']"));
		System.out.println("Discount percentage of the product is :" + offerPercentage.getText());
		File source = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File(".src/main/resources/snaps/SanpDeal01.jpg");
		FileUtils.copyFile(source, dest);
		driver.findElement(By.xpath("//div[contains(@class,'close close1')]")).click();
		driver.switchTo().defaultContent();
		driver.close();
	}

}