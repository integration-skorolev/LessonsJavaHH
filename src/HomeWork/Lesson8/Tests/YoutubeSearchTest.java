package HomeWork.Lesson8.Tests;

import HomeWork.Lesson8.Assertions.Assertions;
import HomeWork.Lesson8.Pages.MatchTVPage;
import HomeWork.Lesson8.Pages.YoutubeMainPage;
import HomeWork.Lesson8.base.BaseTest;
import org.testng.annotations.Test;

public class YoutubeSearchTest extends BaseTest {

  @Test
  public void checkYoutube() {
    MatchTVPage matchTvPage = new YoutubeMainPage()
        .openYouTubePage()
        .searchAny("Матч тв")
        .openMatchTv();
    new Assertions(matchTvPage)
        .title()
        .isSubscribeButtonDisplay();
  }
}
