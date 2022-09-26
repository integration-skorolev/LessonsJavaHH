package HomeWork.Lesson8.Assertions;

import HomeWork.Lesson8.Pages.CommonPage;
import org.testng.Assert;

public class Assertions {

  private CommonPage commonPage;

  public Assertions(CommonPage commonPage) {
    this.commonPage = commonPage;
  }

  public Assertions title() {
    Assert.assertEquals(commonPage.getTitleNameChannel(), commonPage.getNameChannel());
    return this;
  }

  public Assertions isSubscribeButtonDisplay() {
    Assert.assertEquals(commonPage.isCanSubscribe(), "ПОДПИСАТЬСЯ");
    return this;
  }
}
