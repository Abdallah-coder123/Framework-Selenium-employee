package MobileTest;

import Pages.Mobile.Home;
import TestBase.MobileBase.MobileTestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

//@Listeners(MobileTestListener.class)
public class HomeTest extends MobileTestBase {

  @Test(description = "TC001 Positive Scenario", priority = 1)
  public void positiveScenario() {
    Home home = new Home(driver);
    String song = "lil Wayne";
    String expectedResult = "How To Love";
    String actualResult = "Mirror";
    home.shazam(song);

    //        Assertion
    //        >>>>>>>>>
    if (home.songName.isDisplayed()) {
      actualResult = expectedResult;
    }
    Assert.assertEquals(actualResult, expectedResult);
  }

  @Test(description = "TC002 Negative Scenario", priority = 2)
  public void negativeScenario() {
    Home home = new Home(driver);
    String song = "lil Wayne";
    String expectedResult = "Mirror";
    String actualResult = "How To Love";
    home.shazam(song);

    //        Assertion
    //        >>>>>>>>>
    if (!home.songName.isDisplayed()) {
      actualResult = expectedResult;
    }
    Assert.assertEquals(actualResult, expectedResult);
  }
}
