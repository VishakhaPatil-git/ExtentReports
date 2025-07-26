package mavenProject.ExtentReports;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsDemo {
	ExtentReports extent;
	@BeforeTest
	public void config() {
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Test Reports");
		reporter.config().setReportName("Web Automation Report");
		
		 extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Vishakha Patil");
	}
	
	@Test
	public void initialDemo() {
		  ExtentTest test = extent.createTest("Initial Demo");
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("start-maximized"); // open Browser in maximized mode
		option.addArguments("disable-infobars"); // disabling infobars
		option.addArguments("--disable-notification");
		option.addArguments("--disable-extensions"); // disabling extensions
		option.addArguments("--disable-gpu"); // applicable to windows os only
		option.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		option.addArguments("--no-sandbox"); // Bypass OS security model
		option.addArguments("--remote-debugging-port=9222");
		option.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client");
		System.out.println(driver.getTitle());
		driver.quit();
		test.fail("Invalid report");
		
		extent.flush();
		}
	

}
