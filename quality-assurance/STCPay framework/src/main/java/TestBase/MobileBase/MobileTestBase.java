package TestBase.MobileBase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class MobileTestBase {

  public AppiumDriver driver;

  // Emulator
  //    @BeforeClass
  //    public void setUp() throws MalformedURLException {
  //        DesiredCapabilities cap = new DesiredCapabilities();
  //        cap.setCapability("udid", "192.168.149.102:5555");
  //        cap.setCapability("deviceName", "Samsung Galaxy S10");
  //        cap.setCapability("platformName", "Android");
  //        cap.setCapability("platformVersion", "10");
  //        cap.setCapability("automationName", "UiAutomator1");
  ////        cap.setCapability("appActivity", "com.facebook.katana.app.mainactivity.FbSplashScreenActivity");
  ////        cap.setCapability("appPackage", "com.facebook.katana");
  ////        cap.setCapability("newCommandTimeout", 20);
  //        cap.setCapability("app", System.getProperty("user.dir")+"src/main/resources/AndroidApps/Mobile Testing.apk");
  //
  //        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), cap);
  //
  //
  //    }

  //    Real Mobile
  @Test
  public void setUp(ITestContext context) throws MalformedURLException {
    DesiredCapabilities cap = new DesiredCapabilities();
    cap.setCapability("udid", "emulator-5554");
    cap.setCapability("deviceName", "sdk_gphone64_x86_64");
    cap.setCapability("platformName", "Android");
    cap.setCapability("platformVersion", "12");
    cap.setCapability("automationName", "UiAutomator2");
    cap.setCapability("appWaitActivity", "activities.ConfigurationActivity");
    cap.setCapability("unicodeKeyboard", true);
    cap.setCapability("resetKeyboard", true);
    //        cap.setCapability("app", System.getProperty("user.dir") + "\\src\\main\\resources\\AndroidApps\\Shazam.apk");
    cap.setCapability(
      "app",
      "/Users/user/Desktop/Workspace/SeleniumDesignPattern/src/main/resources/AndroidApps/Shazam.apk"
    );
    driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), cap);

    context.setAttribute("AppiumDriver", driver);
  }

  @AfterMethod
  public void quite() {
    driver.quit();
  }
}
