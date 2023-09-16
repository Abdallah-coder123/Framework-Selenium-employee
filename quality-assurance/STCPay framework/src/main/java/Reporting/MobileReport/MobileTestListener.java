//package Reporting.MobileReport;
//
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;
//import io.appium.java_client.AppiumDriver;
//import java.io.File;
//import java.io.IOException;
//import java.util.Base64;
//import java.util.Date;
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//public class MobileTestListener implements ITestListener {
//
//  private static ExtentReports extent = MobileExtentManager.createInstance();
//  public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
//  public static boolean CONSOLE;
//
//  @Override
//  public synchronized void onStart(ITestContext context) {
//    System.out.println("Test scenario started!");
//    ExtentTest extentTest = extent.startTest(
//      "<b><font color=blue>" +
//      "MODULE: </font><br>" +
//      context.getName() +
//      "</b> </br> "
//    );
//    test.set(extentTest);
//  }
//
//  @Override
//  public synchronized void onFinish(ITestContext context) {
//    System.out.println(("Test scenario finished!"));
//    extent.flush();
//  }
//
//  @Override
//  public synchronized void onTestStart(ITestResult result) {
//    System.out.println((result.getMethod().getMethodName() + " started!"));
//  }
//
//  @Override
//  public synchronized void onTestSuccess(ITestResult result) {
//    System.out.println((result.getMethod().getMethodName() + " passed!"));
//    //        String logText = "<b>Test Method: </b> " + result.getMethod().getMethodName() + " <b>Passed</b>";
//    String logText1 = "<b>Scenario: </b>" + result.getMethod().getDescription();
//    //        test.get().log(LogStatus.PASS, logText1);
//    try {
//      LOGWithScreenshot(result, test.get(), LogStatus.PASS, logText1);
//    } catch (IOException e) {}
//  }
//
//  @Override
//  public synchronized void onTestFailure(ITestResult result) {
//    System.out.println((result.getMethod().getMethodName() + " failed!"));
//
//    //        String exceptiionMessage = Arrays.toString(result.getThrowable().getStackTrace());
//    String exceptionMessage = result.getThrowable().getMessage();
//    String logText1 = "<b>Scenario: </b>" + result.getMethod().getDescription();
//    String description =
//      logText1 +
//      "<details><summary><b><font color=red>" +
//      "Click to see details:" +
//      "</font></b></summary>" +
//      exceptionMessage.replaceAll(",", "<br>") +
//      "</details> \n";
//    try {
//      LOGWithScreenshot(result, test.get(), LogStatus.FAIL, description);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
//
//  @Override
//  public synchronized void onTestSkipped(ITestResult result) {
//    System.out.println((result.getMethod().getMethodName() + " skipped!"));
//  }
//
//  @Override
//  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//    System.out.println(
//      (
//        "onTestFailedButWithinSuccessPercentage for " +
//        result.getMethod().getMethodName()
//      )
//    );
//  }
//
//  public static String getScreenshotName(String methodName) {
//    Date date = new Date();
//    String fileName =
//      methodName + "_" + date.toString().replace(":", "_") + ".png";
//    return fileName;
//  }
//
//  public void LOGWithScreenshot(
//    ITestResult iTestResult,
//    ExtentTest logger,
//    LogStatus status,
//    String TestDescription
//  ) throws IOException {
//    String Base64StringofScreenshot = "";
//    ITestContext context = iTestResult.getTestContext();
//    AppiumDriver driver = (AppiumDriver) context.getAttribute("AppiumDriver");
//    if (driver != null) {
//      File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//      byte[] fileContent = FileUtils.readFileToByteArray(src);
//      Base64StringofScreenshot =
//        "data:image/png;base64," +
//        Base64.getEncoder().encodeToString(fileContent);
//    }
//    logger.log(
//      status,
//      TestDescription +
//      "\n" +
//      logger.addBase64ScreenShot(Base64StringofScreenshot)
//    );
//  }
//}
