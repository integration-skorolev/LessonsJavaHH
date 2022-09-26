package HomeWork.Lesson8.Pages;

import HomeWork.Lesson8.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommonPage extends BasePage {

  protected String nameChannel;

  @FindBy(xpath = "//*[@id = 'inner-header-container']//*[@id='container']")
  WebElement titleNameChannel;

  @FindBy(xpath = "//*[@id='inner-header-container']//*[@id='subscribe-button']")
  WebElement subscribeButton;

  public String getTitleNameChannel(){
    return titleNameChannel.getText();
  }

  public String getNameChannel() {
    return nameChannel;
  }

  public String isCanSubscribe(){


    System.out.println(subscribeButton.getText());
    return subscribeButton.getText();
  }
}