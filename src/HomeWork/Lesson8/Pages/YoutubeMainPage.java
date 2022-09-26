package HomeWork.Lesson8.Pages;

import HomeWork.Lesson8.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YoutubeMainPage extends BasePage {

  @FindBy(css = "input[id=search]")
  WebElement searchArea;

  @FindBy(css = "button[id=search-icon-legacy]")
  WebElement runSearchButton;

  public YoutubeMainPage() {
    PageFactory.initElements(driver, this);
  }

  public SearchesResults searchAny(String any) {
    searchArea.click();
    searchArea.sendKeys(any);
    runSearchButton.click();
    return new SearchesResults();
  }

  public YoutubeMainPage openYouTubePage() {
    driver.get("https://www.youtube.com/");
    return this;
  }
}
