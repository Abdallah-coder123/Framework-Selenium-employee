package Pages.Mobile;

import Helpers.ActionsHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class Home {

  public Home(AppiumDriver driver) {
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
  }

  ActionsHelper action = new ActionsHelper();

  @AndroidFindBy(id = "search")
  public WebElement searchBtn;

  @AndroidFindBy(id = "search_src_text")
  public WebElement searchField;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"lil wayne\"]")
  public WebElement hint;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"How To Love\"]")
  public WebElement target;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"How To Love\"]")
  public WebElement songName;

  public void shazam(String song) {
    this.searchBtn.click();
    this.searchField.sendKeys(song);
    this.hint.click();
    this.target.click();
  }
}
