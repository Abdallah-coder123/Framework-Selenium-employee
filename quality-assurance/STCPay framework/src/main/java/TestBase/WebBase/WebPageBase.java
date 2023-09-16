package TestBase.WebBase;

import TestBase.WebBase.Browsers.ChromeBrowser;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class WebPageBase extends WebTestBase {

  public String baseUrl;
  public Properties properties;

  //    Constructor
  public WebPageBase(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  public void openBrowser(
    String browser,
    WebDriver driver,
    ChromeBrowser browserPage
  ) {
    if (browser.equals("chrome")) {
      browserPage.openBrowser();
    } else if (browser.equals("firefox")) {} else if (
      browser.equals("edge")
    ) {} else if (browser.equals("safari")) driver
      .manage()
      .timeouts()
      .implicitlyWait(Duration.ofSeconds(5));
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
  }
}
