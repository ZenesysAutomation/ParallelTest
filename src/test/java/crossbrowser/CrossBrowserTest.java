package crossbrowser;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.handler.timeout.TimeoutException;
 
public class CrossBrowserTest {
WebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public void initialize(String browser) {
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--remote-allow-origins=*");
                driver = new FirefoxDriver(firefoxOptions);
                System.out.println("Firefox is Launched");
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
                System.out.println("Chrome is Launched");
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                driver = new EdgeDriver(edgeOptions);
                System.out.println("Edge is Launched");
                break;
            default:
                throw new IllegalArgumentException("Invalid browser specified: " + browser);
        }
    }
 
	@Test(priority=0)
    public void CINCHCCMlogin() {
        driver.get("https://cinchccm-client-cinch-test.azurewebsites.net/login?returnPage=");
        driver.manage().window().maximize();
        try {
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    		WebElement Useremail= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));
    		Useremail.sendKeys("ankita.chaudhary@zenesys.com");
    		
    		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
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
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		 WebElement Login=driver.findElement(By.xpath("(//span[@class='e-btn-content'])[1]"));
		 wait.until(ExpectedConditions.elementToBeClickable(Login));
		 Login.click();
		 Thread.sleep(5000);
		}
		catch (Exception e) {
			System.out.println("Login button was not clickable within 20 seconds.");
		}
	}
    
    @Test(enabled=false)
    public void Verify_the_Dropdown_and_select_the_Test_Community() throws InterruptedException{
		try {
		WebElement Dropdown=driver.findElement(By.xpath("//span[@role='listbox']"));
		Dropdown.sendKeys("Test Community");
		Thread.sleep(3000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    @Test(priority=3)
    public void Verify_the_Continue_Button() throws InterruptedException {
		try {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement Continue=driver.findElement(By.xpath("//input[@value='Continue']"));
		 wait.until(ExpectedConditions.elementToBeClickable(Continue));
		 Thread.sleep(3000);
		 Continue.click();
		 Thread.sleep(5000); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    
    @AfterTest
    public void endTest() {
        if (driver != null) {
            driver.quit();  
            System.out.println("Browser session is closed");
        }
    }
}







 