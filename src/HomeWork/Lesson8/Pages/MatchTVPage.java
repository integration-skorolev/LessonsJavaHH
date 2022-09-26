package HomeWork.Lesson8.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class MatchTVPage extends CommonPage {
  public static final By path = By.xpath("//div[@id='content-section']//a[@href=\"/c/MatchTV\"]//span[contains(text(),'Официальный YouTube канал «')]");

  public MatchTVPage() {
    PageFactory.initElements(driver, this);
    nameChannel = "Матч ТВ";
  }
}
