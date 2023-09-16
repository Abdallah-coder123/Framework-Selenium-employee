package Pages.Web;

import TestBase.WebBase.WebPageBase;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Offers extends WebPageBase {

  public Offers(WebDriver driver) throws IOException, ParseException {
    super(driver);
  }

  @FindBy(linkText = "Offers")
  public WebElement offersLink;
  //    public ValidatableResponse getOffersAPI() {
  //        driver.get(url);
  //        this.offersLink.click();
  //        return given().when().get(offersURL).then();
  //    }

}
