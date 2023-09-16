package Utils;

import TestBase.TestDataBase.TestDataBase;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retries extends TestDataBase implements IRetryAnalyzer {

  int counter = 0;
  long limit = retries;

  @Override
  public boolean retry(ITestResult result) {
    if (!result.isSuccess()) {
      if (counter < limit) {
        System.out.println(
          "Retrying Test : Re-running " +
          result.getName() +
          " for " +
          (counter + 1) +
          " time(s)."
        );
        counter++;
        result.setStatus(ITestResult.FAILURE);
        return true;
      } else {
        result.setStatus(ITestResult.FAILURE);
      }
    } else {
      result.setStatus(ITestResult.SUCCESS);
    }

    return false;
  }
}
