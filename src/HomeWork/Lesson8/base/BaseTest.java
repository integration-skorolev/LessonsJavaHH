package HomeWork.Lesson8.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

abstract public class BaseTest {
  protected WebDriver driver;

  @BeforeSuite
  public void setUp() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    BasePage.setDriver(driver);
  }

  @AfterSuite
  public void offDriver() {
    driver.close();
    driver.quit();
  }
}
