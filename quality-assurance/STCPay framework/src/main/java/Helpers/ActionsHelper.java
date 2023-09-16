package Helpers;
import TestBase.TestDataBase.TestDataBase;
import com.google.common.net.MediaType;
import io.restassured.path.json.JsonPath;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.*;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import static org.openqa.selenium.remote.http.Contents.utf8String;
public class ActionsHelper extends TestDataBase {
    DateHelper dateHelper = new DateHelper();
    //    Objects
    protected static Logger log = LogManager.getLogger(ActionsHelper.class);
    public String browser;
    public String baseUrl;
    public Properties properties;
    public boolean waitForExistence(WebElement element, String elementName) {
        boolean result = false;
        int counter = 0;
        while (counter < 10) {
            if (element.isDisplayed()) {
                result = true;
                break;
            }
            sleepBySeconds(1);
            counter++;
        }
        verifyResult(elementName + " Not Found!", "Element Exist!", result);
        return result;
    }
    public boolean waitForTableExistence(
            List<WebElement> elements,
            String tableName,
            WebDriver driver,
            String path
    ) {
        boolean isExist = false;
        for (int i=0; i<tableSize(driver,path); i++) {
            waitForExistence(elements.get(i), tableName, driver);
            scrollElementIntoView(driver, elements.get(i));
        }
        return isExist;
    }
    public boolean elementVisibility(
            WebDriver driver,
            int timeOut,
            WebElement element
    ) {
        try {
            WebDriverWait wait = new WebDriverWait(
                    driver,
                    Duration.ofSeconds(timeOut)
            );
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
            Thread.sleep(3000);
            return element.isDisplayed();
        } catch (
                org.openqa.selenium.NoSuchElementException
                | org.openqa.selenium.StaleElementReferenceException
                | org.openqa.selenium.TimeoutException e
        ) {
            return false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void sleepBySeconds(int timeOut) {
        try {
            Thread.sleep(timeOut * 1000);
        } catch (InterruptedException e) {
        }
    }
    public boolean waitForExistence(WebElement element, int timeOut)
            throws InterruptedException {
        boolean result = false;
        int counter = 0;
        while (counter < 60) {
            if (element.isDisplayed()) {
                result = true;
                break;
            }
            sleepBySeconds(timeOut);
            counter++;
        }
        return result;
    }
    public boolean waitForExistence(WebElement element, int timeOut, int sleep)
            throws InterruptedException {
        boolean result = false;
        int counter = 0;
        while (counter < 60) {
            if (element.isDisplayed()) {
                result = true;
                break;
            }
            sleepBySeconds(timeOut);
            counter++;
        }
        Thread.sleep(sleep * 1000);
        return result;
    }
    public void highlightTable(List<WebElement> list, WebDriver driver) {
        try {
            if (!list.isEmpty()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", list);
            }
        } catch (Exception e) {
        }
    }
    public void highlightElement(WebElement element, WebDriver driver) {
        try {
            if (element.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'border:1px solid red; background:yellow')", element);
            }
        } catch (Exception e) {
        }
    }
    public boolean waitForExistence(WebElement element, String elementName, int nTimes, WebDriver driver) {
        boolean isExist = false;
        int count = 1;
        while (count <= nTimes) {
            try {
                //don't sleep the first try
                if (count > 1) {
                    Thread.sleep(1000);
                }
                if (element.isDisplayed()) {
                    highlightElement(element, driver);
                    isExist = true;
                    break;
                }
            } catch (Exception e) {
            }
            count++;
        }
        verifyResult(
                "<b><font color=red>" + elementName + "</b> - not found!</font>",
                "<font color=green>User should be able to find <b>" + elementName + "</b></font>",
                isExist
        );
        return isExist;
    }
    public boolean waitForExistence(
            WebElement element,
            String elementName,
            WebDriver driver
    ) {
        boolean isExist = false;
        int count = 1;
        while (count <= defaultWaitTime) {
            try {
                //don't sleep the first try
                if (count > 1) {
                    Thread.sleep(1000);
                }
                if (element.isDisplayed()) {
                    highlightElement(element, driver);
                    isExist = true;
                    break;
                }
            } catch (Exception e) {
            }
            count++;
        }
        verifyResult(
                "<b><font color=red>" + elementName + "</b> - not found!</font>",
                "<font color=green>User should be able to find <b>" +
                        elementName +
                        "</b></font>",
                isExist
        );
        return isExist;
    }
    public int intercept(String url, WebDriver driver) {
        try {
            // then ask for all the performance logs from this request
            // one of them will contain the Network.responseReceived method
            // and we shall find the "last recorded url" response
            LogEntries logs = driver.manage().logs().get("performance");
            int status = -1;
            for (Iterator<LogEntry> it = logs.iterator(); it.hasNext(); ) {
                LogEntry entry = it.next();
                try {
                    org.json.JSONObject json = new org.json.JSONObject(
                            entry.getMessage()
                    );
                    org.json.JSONObject message = json.getJSONObject("message");
                    String method = message.getString("method");
                    if (method != null && "Network.responseReceived".equals(method)) {
                        org.json.JSONObject params = message.getJSONObject("params");
                        org.json.JSONObject response = params.getJSONObject("response");
                        String messageUrl = response.getString("url");
                        if (messageUrl.contains(url)) {
                            status = response.getInt("status");
                            System.out.println(
                                    "---------- bingo !!!!!!!!!!!!!! returned response for " +
                                            messageUrl +
                                            ": " +
                                            status
                            );
                            System.out.println(
                                    "---------- bingo !!!!!!!!!!!!!! headers: " +
                                            response.get("headers")
                            );
                            break;
                        }
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            System.out.println("\nAPI :" + url);
            System.out.println("\nstatus code: " + status);
            return status;
        } finally {
        }
    }
    public void waitForLoad(String url, int statusCode, WebDriver driver) {
        int counter = 1;
        while (intercept(url, driver) != statusCode && counter <= 60) {
            sleepBySeconds(1);
            counter++;
        }
    }
    public void refreshPage(WebDriver driver) {
        driver.get(driver.getCurrentUrl());
    }
    public void verifyResult(
            String actualResult,
            String expectedResult,
            boolean condition
    ) {
        actualResult = "<b><font color=red>" + actualResult + "</b></font>";
        expectedResult = "<b><font color=green>" + expectedResult + "</b></font>";
        if (condition) {
            actualResult = expectedResult;
        }
        Assert.assertEquals(actualResult, expectedResult);
    }
    public void verifyResult(
            boolean actualResult,
            boolean expectedResult,
            boolean condition
    ) {
        actualResult =
                Boolean.valueOf("<b><font color=red>" + actualResult + "</b></font>");
        expectedResult =
                Boolean.valueOf("<b><font color=green>" + expectedResult + "</b></font>");
        if (condition) {
            actualResult = expectedResult;
        }
        Assert.assertEquals(actualResult, expectedResult);
    }
    //    public void verifyResult(
//            WebElement actualResult,
//            WebElement expectedResult,
//            boolean condition
//    ) {
//        actualResult =
//                "<b><font color=red>" + actualResult.getText() + "</b></font>";
//        expectedResult =
//                "<b><font color=green>" + expectedResult.getText() + "</b></font>";
//        if (condition) {
//            actualResult = expectedResult;
//        }
//        Assert.assertEquals(actualResult, expectedResult);
//    }
    public void verifyInt(
            String actualResult,
            String expectedResult,
            boolean condition
    ) {
        actualResult = "<b><font color=red>" + actualResult + "</b></font>";
        expectedResult = "<b><font color=green>" + expectedResult + "</b></font>";
        if (condition) {
            actualResult = expectedResult;
        }
        Assert.assertEquals(actualResult, expectedResult);
    }
    public void closeBrowser(WebDriver driver) {
        driver.quit();
    }
    public void closeWindow(WebDriver driver) {
        driver.close();
    }
    public void tab(WebDriver driver, By locator) {
        driver.findElement(locator).sendKeys(Keys.TAB);
    }
    public void setText(WebDriver driver, By locator, String text) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }
    public String getText(WebDriver driver, WebElement element) {
        String displayedText = element.getText();
        if (displayedText.isEmpty()) {
            return element.getAttribute("value");
        } else {
            return displayedText;
        }
    }
    public void click(WebDriver driver, By locator) {
        driver.findElement(locator).click();
    }
    public void goBack(WebDriver driver) {
        driver.navigate().back();
    }
    public String getWindowHandle(WebDriver driver) {
        return driver.getWindowHandle();
    }
    public Set<String> getWindowHandles(WebDriver driver) {
        return driver.getWindowHandles();
    }
    public int getNumberOfOpenWindows(WebDriver driver) {
        return driver.getWindowHandles().size();
    }
    public void openNewTab(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.open()");
    }
    public void goToUrl(WebDriver driver, String url) {
        driver.get(url);
    }
    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }
    public boolean waitForPageTitle(WebDriver driver, String title) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.titleContains(title));
    }
    /**
     * Performs a drag-n-drop operation on a given element by a given x,y offset.
     *
     * @param locator The element to be interacted with
     * @param x       x-coordinate
     * @param y       Y-coordinate
     */
    public void dragAndDropByOffset(WebDriver driver, By locator, int x, int y) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(locator);
        actions.dragAndDropBy(element, x, y).perform();
    }
    public void dismissPopup(WebDriver driver) {
        driver.switchTo().alert().dismiss();
    }
    public void acceptPopup(WebDriver driver) {
        driver.switchTo().alert().accept();
    }
    public void setAlertText(WebDriver driver, String text) {
        driver.switchTo().alert().sendKeys(text);
    }
    public void waitForElementText(WebDriver driver, By locator, String text) {
        // This is an explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBe(locator, text));
        // This is a FluentWait. It does the same as the above wait, but it is more customizable
        //        Wait<WebDriver> wait = new FluentWait<>(driver)
        //                .withTimeout(Duration.ofSeconds(3))
        //                .pollingEvery(Duration.ofMillis(250))
        //                .ignoring(NoSuchElementException.class);
        //        wait.until(ExpectedConditions.textToBe(locator, text));
    }
    public void hoverOverElement(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
    public void scrollElementIntoView(WebDriver driver, String locator) {
        WebElement element = driver.findElement(By.xpath(locator));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }
    public void scrollElementIntoView(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }
    /**
     * Scrolls the document by the specified number of pixels.
     *
     * @param x How many pixels to scroll by, along the x-axis (horizontal).
     * @param y How many pixels to scroll by, along the y-axis (vertical).
     */
    public void scrollPage(WebDriver driver, int x, int y) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(" + x + "," + y + ");");
    }
    /**
     * Takes screenshot of whole page and uses the current date/time as the file name
     */
    //  public void takeScreenshot() {
    //    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
    //      "MM-dd-yyyy HH-mm-ss-SSS"
    //    );
    //    LocalDateTime dateTime = LocalDateTime.now();
    //    takeScreenshot(dateTime.format(formatter));
    //
    //  }
    /**
     * Takes screenshot of whole page and allows you to name the screenshot
     *
     * @param screenshotName The screenshot file name
     */
    public void takeScreenshot(WebDriver driver, String screenshotName) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(
                    file,
                    new File(
                            "src/main/resources/Reports/WebReports/" + screenshotName + ".png"
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Takes screenshot of single WebElement
     */
    public void takeElementScreenshot(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        File file = element.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("./screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @param frame The index of the frame to switch to (first frame has index 0)
     */
    public void switchFrames(WebDriver driver, int frame) {
        driver.switchTo().frame(frame);
    }
    public void switchToDefaultFrame(WebDriver driver) {
        driver.switchTo().defaultContent();
    }
    public void setCookie(WebDriver driver, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        driver.manage().addCookie(cookie);
    }
    public Cookie getCookie(WebDriver driver, String name) {
        return driver.manage().getCookieNamed(name);
    }
    public String getJSONString(String resBody, String jsonPath) {
        return JsonPath.from(resBody).get(jsonPath);
    }
    public boolean getJSONBoolean(String resBody, String jsonPath) {
        boolean b = JsonPath.from(resBody).get(jsonPath);
        return b;
    }
    public int getJSONInt(String resBody, String jsonPath) {
        return JsonPath.from(resBody).get(jsonPath);
    }
    public void verifyResult(
            String not_english_language,
            String it_is_english,
            Character.UnicodeBlock forName
    ) {
    }
    public void getApi(WebDriver driver) {
        NetworkInterceptor interceptor = new NetworkInterceptor(
                driver,
                Route.matching(req -> req.getUri().contains("myRequestUrl"))
                        .to(() -> req -> new HttpResponse()
                                .setStatus(200)
                                .addHeader("Content-Type", MediaType.HTML_UTF_8.toString())
                                .setContent(utf8String("Hello, World!"))));
    }
    public List<WebElement> getTableRow(WebDriver driver, int tr) {
        List elList = new ArrayList<>();
        for (int i = 1; i <= tr; i++) {
            String path = "//tbody[@class = 'ui-datatable-data ui-widget-content']/tr";
            elList.add((driver.findElement(By.xpath(path))));
        }
        System.out.println("rowws >>>>>>>>>>>> " + elList);
        return elList;
    }
    public List<WebElement> getElList(WebDriver driver, int tr, int td) {
        String path = "";
        List elList = new ArrayList<>();
        for (int i = 1; i <= tr; i++) {
            path = "//tr[" + i + "]/td[" + td + "]/span";
            elList.add((driver.findElement(By.xpath(path))));
        }
        return elList;
    }
    public List<WebElement> getElList(WebDriver driver, int tr, int td, String path) {
        List elList = new ArrayList<>();
        for (int i = 1; i <= tr; i++) {
            elList.add((driver.findElement(By.xpath(path))));
        }
        return elList;
    }
    public List<LocalDate> getDateList(WebDriver driver, int tr, int td) throws ParseException {
        String path = "";
        List elList = new ArrayList<>();
        for (int i = 1; i <= tr; i++) {
            path = "//tr[" + i + "]/td[" + td + "]/span";
            String isoDate = driver.findElement(By.xpath(path)).getText();
            LocalDate date = dateHelper.convertDate(isoDate);
            elList.add(date);
        }
        System.out.println("date >>>>>> " + elList);
        return elList;
    }
    public boolean isElementExist(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
    public List<String> getStringList(WebDriver driver, int tr, int td, String page) {
        String path = "";
        List<String> elList = new ArrayList<>();
        for (int i = 1; i <= tr; i++) {
            if (page.equalsIgnoreCase("Case List")) {
                path = "//tr[" + i + "]/td[" + td + "]/span";
            } else if (page.equalsIgnoreCase("Maker")) {
                path = "//tr[" + i + "]/td[" + td + "]";
            }
            elList.add((driver.findElement(By.xpath(path))).getText());
            System.out.println("actions helper >>>>> " + elList);
        }
        return elList;
    }
    public List<WebElement> getElList(WebDriver driver, int size, String listPath) {
        String path = "";
        List elList = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            path = listPath;
            elList.add((driver.findElement(By.xpath(path))).getText());
        }
        return elList;
    }
    public int tableSize(WebDriver driver, String tablePath) {
        List<WebElement> table = driver.findElements(By.xpath(tablePath));
        int size = table.size();
        return size;
    }
    public void VerifyTableContent(int tableSize, List<WebElement> el, String expResult, String data) {
        for (int i = 0; i < tableSize; i++) {
            verifyResult(
                    el.get(i).getText(),
                    expResult,
                    el.get(i).isDisplayed() &&
                            el.get(i).getText().contains(data)
            );
        }
    }
    public boolean VerifyTableContent( WebDriver driver, List<WebElement> table, String tablePath, String value) {
        int size = tableSize(driver, tablePath);
        for (int i = 0; i < size; i++) {
            if (!table.get(i).getText().equalsIgnoreCase(value)) return false;
        }
        return true;
    }
    public boolean VerifyDateContent( WebDriver driver, List<LocalDate> table, String tablePath, LocalDate start, LocalDate end) {
        int size = tableSize(driver, tablePath);
        for (int i = 0; i < size; i++) {
            LocalDate date = table.get(i);
            if (!date.isAfter(start) && date.isBefore(end) ) return false;
        }
        return true;
    }
}