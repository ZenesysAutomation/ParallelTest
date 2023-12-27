package base;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
 
public class BaseTest {
    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
    public static String remote_url = "http://192.168.50.151:4444/";
    public Capabilities capabilities;
 
    
    @Parameters({"browser"})
    @BeforeMethod
    public void setDriver(String browser) throws MalformedURLException {
         
        System.out.println("Test is running on "+browser);
        if(browser.equals("firefox")) {
        	FirefoxOptions options= new FirefoxOptions();
			options.addArguments("--remote-allow-origins=*");
            capabilities = new FirefoxOptions();
        } else if (browser.equals("chrome")) {
        	ChromeOptions options= new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
            capabilities = new ChromeOptions();
        } else if (browser.equals("edge")) {
        	EdgeOptions options= new EdgeOptions();
			options.addArguments("--remote-allow-origins=*");
            capabilities = new EdgeOptions();
        }
 
        driver.set(new RemoteWebDriver(new URL(remote_url), capabilities));
        driver.get().get("https://cinchccm-client-cinch-test.azurewebsites.net/login?returnPage=");
        driver.get().manage().window().maximize();
        driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
       /*WebDriverWait wait = new WebDriverWait(driver.get(),Duration.ofSeconds(10));
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Sign In']")));*/
    
    }
 
    public WebDriver getDriver() {
      return driver.get();
    }
 
    @AfterMethod
    public  void closeBrowser() {
        driver.get().quit();
        driver.remove();
    }
}

