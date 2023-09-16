//package Reporting.MobileReport;
//
//import com.relevantcodes.extentreports.ExtentReports;
//
//public class MobileExtentManager {
//
//  private static ExtentReports extent;
//  public static String reportFileName =
//    "Mobile_Report_" + System.currentTimeMillis() + ".html";
//  private static String path =
//    System.getProperty("user.dir") +
//    "\\src\\main\\resources\\Reports\\MobileReports\\";
//
//  public static ExtentReports createInstance() {
//    extent = new ExtentReports(path + reportFileName);
//    extent.addSystemInfo("Author", "DIS");
//    extent.addSystemInfo("Enviroment", "Mobile Test Result");
//    extent.addSystemInfo("Test", "Home AUTO TEST");
//    extent.addSystemInfo("POWEREDBY:", "DIS");
//
//    return extent;
//  }
//}
