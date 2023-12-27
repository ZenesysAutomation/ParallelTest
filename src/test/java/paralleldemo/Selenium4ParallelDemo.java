package paralleldemo;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import base.BaseTest;
import io.netty.handler.timeout.TimeoutException;
 
public class Selenium4ParallelDemo extends BaseTest {
 
    @Test(priority=0)
    public void validCredentials()  {
    	
    	  try {
      		WebDriverWait wait = new WebDriverWait(driver.get(),Duration.ofSeconds(20));
      		WebElement Useremail= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));
      		Useremail.sendKeys("ankita.chaudhary@zenesys.com");
      		
      		WebDriverWait wait2 = new WebDriverWait(driver.get(),Duration.ofSeconds(20));
      		WebElement Password= wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='password']")));
      		Password.sendKeys("Testing@123");
      		
      		}
      		catch (TimeoutException e ) {
      			 e.printStackTrace();     
      }
          
      }  
    
    @Test(priority=1)
    public void click_on_the_login_button() throws InterruptedException {
		try {
		 WebDriverWait wait = new WebDriverWait(driver.get(),Duration.ofSeconds(20));
		 WebElement Login=driver.get().findElement(By.xpath("(//span[@class='e-btn-content'])[1]"));
		 wait.until(ExpectedConditions.elementToBeClickable(Login));
		 Login.click();
		 Thread.sleep(5000);
		}
		catch (Exception e) {
			System.out.println("Login button was not clickable within 20 seconds.");
		}
	}	
 
    }

 
    