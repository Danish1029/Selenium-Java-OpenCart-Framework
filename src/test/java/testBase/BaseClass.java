package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public ResourceBundle rb;// to read config.properties
	public String getBrowserName() {
	    return browserName;
	}
	
	
	
   public static WebDriver driver; // make it static so that you can use same instance in Extent report manager
    protected WebDriverWait wait;
    protected Logger logger;
    protected Properties p;
    protected String browserName;
    
 
    @BeforeClass (groups= {"Sanity","Regression","Master"}) //Step8 groups added
    @Parameters({"os","browser"})  // getting browser parameter from testng.xml
    public void setup(
            @Optional("windows") String os,
            @Optional("chrome") String br)
            throws IOException, InterruptedException {

        // setup code
    
    	System.out.println("OS = " + os);
    	System.out.println("Browser = " + br);
 /*   	System.out.println("Execution Env = "
    	        + p.getProperty("execution_env"));  */

        try (FileReader file =
                     new FileReader("./src/test/resources/config.properties")) {

            p = new Properties();
            p.load(file);
        }

        logger = LogManager.getLogger(this.getClass());

        logger.info("Launching browser: {}", br);
        
        /*..............Selenium Grid "Remote environment" setup starts here......*/
        /*..............Selenium Grid Remote Environment Setup Starts Here..............*/

        String executionEnv =
                p.getProperty("execution_env");
        

        // ==========================
        // Remote Execution (Grid)
        // ==========================

        if (executionEnv.equalsIgnoreCase("remote")) 
        {
        	
        	
            MutableCapabilities capabilities;

            // Browser Configuration

            switch (br.toLowerCase())
            {
            case "chrome":

                ChromeOptions chromeOptions =
                        new ChromeOptions();

                chromeOptions.addArguments(
                        "--no-sandbox");

                chromeOptions.addArguments(
                        "--disable-dev-shm-usage");

                chromeOptions.addArguments(
                        "--window-size=1920,1080");

                capabilities = chromeOptions;

                browserName = "Chrome";

                break;

            case "edge":

                EdgeOptions edgeOptions =
                        new EdgeOptions();

                capabilities = edgeOptions;

                browserName = "Edge";

                break;

            case "firefox":

                FirefoxOptions firefoxOptions =
                        new FirefoxOptions();

                capabilities = firefoxOptions;

                browserName = "Firefox";

                break;

            default:

                throw new IllegalArgumentException(
                        "Unsupported browser : " + br);
        }
            // Platform Configuration

            if (os.equalsIgnoreCase("windows"))
            {
                capabilities.setCapability(
                        "platformName",
                        "Windows");
            }
            else if (os.equalsIgnoreCase("mac"))
            {
                capabilities.setCapability(
                        "platformName",
                        "macOS");
            }
            else if (os.equalsIgnoreCase("linux"))
            {
                capabilities.setCapability(
                        "platformName",
                        "Linux");
            }
            else
            {
                throw new IllegalArgumentException(
                        "Unsupported OS : " + os);
            }

            logger.info(
                    "Starting Remote Execution");

            logger.info(
                    "Browser : " + br);

            logger.info(
                    "Operating System : " + os);

            driver =
                    new RemoteWebDriver(
                            new URL(
                                    "http://localhost:4444"),
                            capabilities);

            logger.info(
                    "Connected to Selenium Grid");
        }
        /*..............Selenium Grid environment setup ends here......*/
        /*.............. Remote Environment Ends Here..............*/
        // ==========================
        // Local Execution
        // ==========================
        /*..............Local environment setup starts here......*/
        
        else if (executionEnv.equalsIgnoreCase("local")) {
            
        	  logger.info(
                      "Connected to Selenium local env");
          

          switch (br.toLowerCase()) {

              case "chrome":
              	 browserName = "Chrome";
                  driver = new ChromeDriver();
                  driver.manage().window().maximize();

                  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                  break;

              case "edge":
              	browserName = "Edge";
                  driver = new EdgeDriver();
                  driver.manage().window().maximize();

                  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                  break;

              case "firefox":
              	 browserName = "Firefox";
                  driver = new FirefoxDriver();
                  driver.manage().window().maximize();

                  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                  break;

              default:
                  throw new IllegalArgumentException(
                          "Invalid browser name: " + br);
                 }
          
          logger.info("Starting Local Execution");

          logger.info("Browser : {}", br);
          }
          
          else {

              throw new RuntimeException(
                      "Invalid execution_env value in config.properties");
          }
        	
        	if(driver == null)
        	{
        	    throw new RuntimeException(
        	            "Driver was not initialized. " +
        	            "Browser=" + br +
        	            ", OS=" + os +
        	            ", Env=" + p.getProperty("execution_env"));
        	}
        	
            // ==========================
        // Common Setup
        // ==========================

        driver.manage().deleteAllCookies();

        int implicitWait =
                Integer.parseInt(
                        p.getProperty(
                                "implicitWait",
                                "10"));

        int explicitWait =
                Integer.parseInt(
                        p.getProperty(
                                "explicitWait",
                                "10"));

        driver.manage().timeouts()
                .implicitlyWait(
                        Duration.ofSeconds(
                                implicitWait));

        driver.manage().timeouts()
                .pageLoadTimeout(
                        Duration.ofSeconds(30));

        wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(
                                explicitWait));

        driver.manage()
                .window()
                .setSize(
                        new Dimension(
                                1920,
                                1080));

        logger.info("Opening URL");

        driver.get(
                p.getProperty("appURL2"));// get url from config.properties file
        
 //       driver.get(p.getProperty("appURL1"));
        
    	System.out.println("Title = " + driver.getTitle());

    	System.out.println("URL = " + driver.getCurrentUrl());


    	Thread.sleep(1000);
  
        
        new WebDriverWait(
                driver,
                Duration.ofSeconds(30))
                .until(webDriver ->
                        ((JavascriptExecutor) webDriver)
                                .executeScript(
                                        "return document.readyState")
                                .equals("complete"));
        
        logger.info("URL = {}", driver.getCurrentUrl());

        logger.info("Title = {}", driver.getTitle());

        logger.info(
                "Browser : {}",
                browserName);
        
        logger.info("ReadyState = {}",
                ((JavascriptExecutor)driver)
                        .executeScript(
                                "return document.readyState"));

  /*      logger.info("Page Source Length = {}",
                driver.getPageSource().length());
                
          System.out.println(
        "Page Source Length = "
        + driver.getPageSource().length());      
                 
                 */
        
        logger.info(
                "My Account Present = {}",
                driver.getPageSource().contains("My Account"));
        
        System.out.println(
                "Window Size = "
                + driver.manage()
                        .window()
                        .getSize());
        
        Thread.sleep(5000);

        File src =
                ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(
                src,
                new File("homepage.png"));
        
        logger.info("Current URL: {}", driver.getCurrentUrl());

        logger.info("Page Title: {}", driver.getTitle());

 /*       System.out.println("URL = " + driver.getCurrentUrl());

        System.out.println("Title = " + driver.getTitle()); */
        
    
        
    }
    
    
    public byte[] getScreenshotBytes() {

        return ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
    }
    
    @AfterClass (groups= {"Sanity","Regression","Master"})
    public void tearDown() {

        if (driver != null) {
            logger.info("Closing browser");
            driver.quit();
            driver = null;
        }
    }

    public String randomString() {
        return RandomStringUtils.insecure().nextAlphabetic(6);
    }

    public String randomNumber() {
        return RandomStringUtils.insecure().nextNumeric(10);
    }

    public String randomAlphaNumeric() {
        return RandomStringUtils.insecure().nextAlphabetic(6)
                + "@"
                + RandomStringUtils.insecure().nextNumeric(10);
    }

    public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}
	
}

/*   
 public class BaseClass {
	
	public ResourceBundle rb;// to read config.properties
	
	public Logger logger;// for Logging
	
	public static WebDriver driver;  // make it static so that you can use same instance in Extent report manager
	

	@BeforeClass(groups = { "Master", "Sanity", "Regression" }) //Step8 groups added
	@Parameters("browser")   // getting browser parameter from testng.xml
	public void setup(String br)
	{
		rb = ResourceBundle.getBundle("config");// Load config.properties
				
		logger = LogManager.getLogger(this.getClass());// for Logger  
		
		//launch right browser based on parameter
		if (br.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (br.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
					
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(rb.getString("appURL")); // get url from config.properties file
		driver.manage().window().maximize();
	}

      */
