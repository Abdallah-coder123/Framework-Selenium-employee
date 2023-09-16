package TestBase.WebBase.Browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

public class ChromeBrowser implements Browser {

  public WebDriver driver;

  //    public WebDriver chromeDriver() {
  // Debug ChromeDriver
  //            System.setProperty("webdriver.chrome.verboseLogging", "true");

  // Run in headless mode
  //                ChromeOptions options = new ChromeOptions();
  //                options.addArguments("--headless");
  //                options.addArguments("--window-size=1920,1080");
  //                driver = new ChromeDriver(options);

  // Change download default directory
  //                ChromeOptions options = new ChromeOptions();
  //                Map<String, Object> prefs = new HashMap<String, Object>();
  //                prefs.put("download.default_directory", "<directory such as C:\\MyFolder\\");
  //                options.setExperimentalOption("prefs", prefs);
  //                driver = new ChromeDriver(options);

  // Add Options
  // ++ Disable message 'Chrome is being controlled by automated test software'

  //        return driver;
  //
  //    }

  //  Move to WebPageBase
  //    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
  //    driver.manage().timeouts();
  //        driver.manage().window().maximize();

  @Override
  public WebDriver openBrowser() {
    WebDriverManager.chromedriver().setup();

    ChromeOptions options = new ChromeOptions();
    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
    options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
    options.addArguments(
      "ignore-certificate-errors",
      "ignore-urlfetcher-cert-requests"
    );
    options.addArguments("enable-automation");
    options.addArguments("start-maximized");
    options.addArguments("disable-infobars");
    options.setAcceptInsecureCerts(true);
    driver = new ChromeDriver(options);

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    return driver;
  }
}
