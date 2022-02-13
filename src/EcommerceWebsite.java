import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EcommerceWebsite {

	public static void main(String[] args) {
	
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Shree\\Downloads\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		
		//Adding items to cart
		int j=0;
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		System.out.println(driver.getTitle());
		
		String[] neededVeg= {"Brocolli", "Cauliflower", "Cucumber"};
		List <WebElement> products= driver.findElements(By.xpath("//h4[@class=\"product-name\"]"));
		
		for(int i=0; i<products.size(); i++)
		{
			String[] product_name=products.get(i).getText().split("-");
			String productNeeded=product_name[0].trim();
			List<String> neededVeggies=Arrays.asList(neededVeg);
			if(neededVeggies.contains(productNeeded))
					{
				      driver.findElements(By.xpath("//button[contains (text(), 'ADD TO CART')]")).get(i).click();
				      j++;
				      
				      if(j==3)
				      {
				    	  break;
				      }
					}	
		}
		
		
		//Going to cart
		WebElement cart= driver.findElement(By.cssSelector("a.cart-icon"));
		cart.click();
		
		//Proceeding to checkout
		driver.findElement(By.xpath("//button[contains (text(), 'PROCEED TO CHECKOUT')]")).click();
		
		
		//Applying promo code
		driver.findElement(By.xpath("//input[@placeholder=\"Enter promo code\"]")).sendKeys("rahulshettyacademy");
		driver.findElement(By.cssSelector("button.promoBtn")).click();
		
		//Explicit wait
		WebDriverWait w= new WebDriverWait(driver, 5);
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"promoInfo\"]")));
		
		
		//Promo code validation
		String promoValidation= driver.findElement(By.xpath("//span[@class=\"promoInfo\"]")).getText();
		System.out.println(promoValidation);
		
		//Placing order
		driver.findElement(By.xpath("//button[contains(text(), 'Place Order')]")).click();
		
		WebElement country= driver.findElement(By.xpath("//div/select"));
		country.click();
		Select select= new Select(country);
		select.selectByValue("India");
		
		driver.findElement(By.cssSelector("input.chkAgree")).click();
		
		driver.findElement(By.xpath("//button[contains (text(), 'Proceed')]")).click();
		
	
	}		
}