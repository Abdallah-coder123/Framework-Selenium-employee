package Reporting.WebReport;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

  private static ExtentReports extent;
  public static String reportFileName =
    "STCPay-Automation_" + System.currentTimeMillis() + ".html";
  private static String path =
    System.getProperty("user.dir") + "/src/main/resources/Reports/WebReports/";

  public static ExtentReports getInstance() {
    if (extent == null) createInstance();
    return extent;
  }

  public static ExtentReports createInstance() {
    extent = new ExtentReports(path + reportFileName);
    extent.addSystemInfo("Author", "QA Automation Team");
    extent.addSystemInfo("Environment", "STCPay Test Result");
    extent.addSystemInfo("Test", "WebApp AUTO TEST");
    extent.addSystemInfo("POWEREDBY:", "STCPay");

    return extent;
  }
}
