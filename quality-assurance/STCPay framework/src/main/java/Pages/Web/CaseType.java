package Pages.Web;

public enum CaseType {
  all("all", 0),
  remittance("remittance", 1),
  onboarding("onboarding", 2),
  merchantOnboarding("merchantOnboarding", 3);

  private final String caseType;
  private final int id;

  CaseType(String caseType, int code) {
    this.caseType = caseType;
    this.id = code;
  }

  public String getCaseType() {
    return caseType;
  }

  public int getId() {
    return id;
  }
}
