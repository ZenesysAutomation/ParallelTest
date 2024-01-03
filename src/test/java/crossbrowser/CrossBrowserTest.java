package crossbrowser;
import static org.testng.Assert.assertEquals;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
                //firefoxOptions.addArguments("--remote-allow-origins=*");
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
    

	@Test(priority=0,description="Verify the login functionality")
    public void CINCHCCMlogin() {
        driver.get("https://cinchccm-client-cinch-test.azurewebsites.net/login?returnPage=");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
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
    
    @Test(priority=1,description="Verify the login button")
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
    
    @Test(enabled=false,description= "verify the dropdown list")
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
    
    @Test(priority=3,description="verify the continue button")
    public void Verify_the_Continue_Button() throws InterruptedException {
		try {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement Continue=driver.findElement(By.xpath("//input[@value='Continue']"));
		 wait.until(ExpectedConditions.elementToBeClickable(Continue));
		 Thread.sleep(3000);
		 Continue.click();
		 Thread.sleep(10000); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    @Test(priority=4,description="verify that manage button")
    public void click_on_the_manage() throws InterruptedException {	
    	WebElement managebutton=driver.findElement(By.xpath("(//a[normalize-space()='Manage'])[1]"));
    	managebutton.click();
    	Thread.sleep(6000);
		}
		   	
    @Test(priority=5,description="verify the community note")
	public void click_on_the_add_new_community_note() throws InterruptedException {
	WebElement AddcomNote=driver.findElement(By.xpath("//a[normalize-space()='Add New Community Note']"));
	AddcomNote.click();
	Thread.sleep(5000);  
}
    
    @Test(priority=6,description="verify the note textfield")
	public void enter_the_note() throws InterruptedException {
	try {
	WebElement note=driver.findElement(By.xpath("//textarea[@id='note']"));
	note.sendKeys("note for testing");
	Thread.sleep(2000);
	}
	catch (Exception e) {
		e.printStackTrace();
	}
    
		}
    
    @Test(priority=7,description="verify the communities dropdown")
	public void select_communities() throws InterruptedException {
	try {
	WebElement community=driver.findElement(By.xpath("//input[@id='community']"));
    community.click();
    community.sendKeys("New England Club");
    
	}
	catch (Exception e) {
		e.printStackTrace();
	}	   
		}
    
    
    @Test(priority=8,description="verify the start date")
	public void choose_a_start_date() throws InterruptedException {
		try {	
	WebElement startDateField = driver.findElement(By.xpath("//input[@id='startdate']"));
	  LocalDate currentDate = LocalDate.now();
	  String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
	  startDateField.sendKeys(formattedDate);
	  Thread.sleep(3000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    
    @Test(priority=9,description="verify an end date")
	public void choose_an_end_date() throws InterruptedException {
	
	LocalDateTime targetDate = LocalDateTime.now().plusDays(generateRandomNumber(2,100));
    String targetDateString = targetDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

    WebElement endDateField = driver.findElement(By.xpath("//input[@id='enddate']"));
    endDateField.click();

    for (int i = 1; i <= 10; i++) {
        endDateField.sendKeys(Keys.BACK_SPACE);
    }
    
   endDateField.sendKeys(targetDateString);
   Thread.sleep(6000);		
	}
	private static int generateRandomNumber(int start, int end) {
    Random random = new Random(); 
    if (start > end) {
        throw new IllegalArgumentException("Start value must be less than or equal to the end value.");
    }
    int range = end - start + 1;
    int randomNumber = random.nextInt(range) + start;
    return randomNumber;
    
	}
	
	
	@Test(priority=10,description="verify the save button")
	public void click_on_the_save_button() throws InterruptedException {
		try {
	WebElement savebtn=driver.findElement(By.cssSelector("button[type='submit']"));
	savebtn.click();
    Thread.sleep(5000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority=11,description="validation message should be shown")
	public void community_note_was_created_message_should_be_shown() throws Exception {
	try {
	String expectedResult= "Community Note was created.";
	String actualResult= driver.findElement(By.xpath("//div[@class='e-toast-content']")).getText();
	assertEquals("Community note",expectedResult,actualResult);
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







 