package week4.day2;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {
	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro", Keys.ENTER);
		WebElement element = driver.findElement(By.xpath("//span[@class='a-price-whole']"));
		String Price = element.getText();
		System.out.println("Price of the mobile is " + Price);
		WebElement element2 = driver.findElement(By.xpath("//span[@class='a-size-base s-underline-text']"));
		String Ratings = element2.getText();
		System.out.println("Number of customer ratings " + Ratings);
		driver.findElement(By.xpath("//a[@href='javascript:void(0)']//i")).click();
		WebElement RatingTable = driver.findElement(By.id("histogramTable"));
		List<WebElement> Rows = RatingTable.findElements(By.tagName("tr"));
		WebElement firstRow = Rows.get(1);
		int TotalColumn = firstRow.findElements(By.tagName("td")).size();
		System.out.println("Number of Columns: " + TotalColumn);
		System.out.println("Number of rows: " + Rows.size());
		for (int i = 0; i < Rows.size(); i++) {
			WebElement currentRow = Rows.get(i);
			List<WebElement> listColumns = currentRow.findElements(By.tagName("td"));
			for (int j = 0; j < listColumns.size(); j++) {
				if (listColumns.get(j).getText().contains("5 star")) {
					System.out.println("Percentage of 5 Star rating is " + listColumns.get(2).getText());
				}

			}
		}
		driver.findElement(By.xpath("//span[contains(@class,'a-size-medium a-color-base')]")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(windowHandles);
		String secondWindow = list.get(1);
		driver.switchTo().window(secondWindow);

		WebElement Image = driver.findElement(By.id("imgTagWrapperId"));
		File source = Image.getScreenshotAs(OutputType.FILE);
		File dest = new File(".src/main/resources/snaps/Amazon.jpg");
		FileUtils.copyFile(source, dest);
		driver.findElement(By.id("add-to-cart-button")).click();
		WebElement CartTotal = driver.findElement(By.id("attach-accessory-cart-subtotal"));
		String CartPrice = CartTotal.getText();
		System.out.println(CartPrice);
		Thread.sleep(3000);
		driver.close();
		if (CartPrice.equals(Price)) {
			System.out.println("Cart sub total is verified");
		}

	}

}
