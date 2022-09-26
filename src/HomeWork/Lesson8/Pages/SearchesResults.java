package HomeWork.Lesson8.Pages;

public class SearchesResults extends CommonPage {

  public MatchTVPage openMatchTv() {
    driver.findElement(MatchTVPage.path).click();
    return new MatchTVPage();
  }
}

