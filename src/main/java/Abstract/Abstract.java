package Abstract;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class Abstract  implements IRetryAnalyzer {

    public static WebDriver driver;
    public String browserName = System.getProperty("browserName");
    public String deviceName = System.getProperty("deviceName");
    public final Properties configProp = new Properties();
    public String baseURL = "http://automationpractice.com/";
    public static int waitDuration = 10;
    private int count = 0;
    private static int maxTry = 3;
    public static String failedMessage;
    public Logger log= LogManager.getLogger(getClass().getName());
    public InputStream inputStream;

    @BeforeClass
    public void setUp() {
        if (browserName == "chrome") {
            System.setProperty("webdriver.chrome.driver", "C:/Users/meren/OneDrive/Masaüstü/Selenium/ChromeDriver/chromedriver.exe");
            driver.manage().window().maximize();
            driver.get(baseURL);
            MDC.put("BrowserName", "Chrome");
            inputStream = getClass().getClassLoader().getResourceAsStream("browserElement.properties");
            // configProp.load(inputStream)
            // new InputStreamReader(inputStream, "UTF-8");
            log.info("BROWSER AÇILDI");




        } else if (browserName == "responsive") {
            try {
                Map<String, String> mobileEmulation = new HashMap<String, String>();
                mobileEmulation.put("deviceName", deviceName);
                Map<String, Object> chromeOptions = new HashMap<String, Object>();
                chromeOptions.put("mobileEmulation", mobileEmulation);
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                System.setProperty("webdriver.chrome.driver", "C:/Users/meren/OneDrive/Masaüstü/Selenium/ChromeDriver/chromedriver.exe");
                driver.manage().window().maximize();
                driver.get(baseURL);
                MDC.put("BrowserName", "Responsive");
                MDC.put("DeviceName", deviceName);
                inputStream = getClass().getClassLoader().getResourceAsStream("responsiveElement.properties");
                configProp.load(inputStream);
                new InputStreamReader(inputStream, "UTF-8");
            } catch (NoClassDefFoundError e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info("BROWSER AÇILDI");
        } else {
            System.setProperty("webdriver.chrome.driver", "C:/Users/meren/OneDrive/Masaüstü/Selenium/ChromeDriver/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(baseURL);
            MDC.put("BrowserName", "Chrome");
        }

    }
    //Close Driver
    @AfterClass
    public void teardown() {
        /*driver.quit();
        log.info("Browser kapatıldı");*/
    }

    //Find İnvestTest Result
    @AfterMethod
    public void testResult(ITestResult result) {
        if (result.isSuccess() == true) {
            log.info("PASSED");
            String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
            System.setProperty(ESCAPE_PROPERTY, "false");
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // FileUtils.copyFile(scrFile, new File("screenshot/" + result.getName() + ".png"));
        } else {
            log.error("FAILED");
            failedMessage = result.getThrowable().toString();
            // mailsend(result.getName(), result.getName())
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            //FileUtils.copyFile(scrFile, new File("screenshot/" + result.getName() + ".png"));
            //add screenshot in reportng
            String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
            System.setProperty(ESCAPE_PROPERTY, "false");
            Reporter.setCurrentTestResult(result);
            Reporter.log("<img style='width:99%;height:99%;' src=" + new File("screenshot/" + result.getName() + ".png").getAbsolutePath() + ">");
        }
    }
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            if (count < maxTry) {
                count++;
                result.setStatus(ITestResult.FAILURE);
                log.info("Test Fail");
                return true;
            } else {
                result.setStatus(ITestResult.FAILURE);
                log.info("Test Fail");
            }
        } else {
            result.setStatus(ITestResult.SUCCESS);
            log.info("Test Passed");
        }
        return false;
    }

}