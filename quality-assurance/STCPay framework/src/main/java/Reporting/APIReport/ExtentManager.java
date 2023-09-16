package Reporting.APIReport;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

  private static ExtentReports extent;
  public static String reportFileName =
    "STCPay-API" + System.currentTimeMillis() + ".html";
  public static String path =
    System.getProperty("user.dir") + "/src/main/resources/Reports/APIReports/";

  public static ExtentReports getInstance() {
    if (extent == null) createInstance();
    return extent;
  }

  public static ExtentReports createInstance() {
    extent = new ExtentReports(path + reportFileName);
    extent.addSystemInfo("Author", "DIS - QA Automation Team");
    extent.addSystemInfo("Environment", "STCPay Test Result");
    extent.addSystemInfo("Test", "STCPay API Test");
    extent.addSystemInfo("POWEREDBY:", "DIS");

    return extent;
  }
}
