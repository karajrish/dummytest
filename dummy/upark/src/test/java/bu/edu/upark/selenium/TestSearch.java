package bu.edu.upark.selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestSearch {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSearch() throws Exception {
    driver.get(baseUrl + "/upark/#/");
    driver.findElement(By.cssSelector("span.ng-binding")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("rayli@bu.edu");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("happyrl890");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(4000); 
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("happyrl");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(4000); 
    driver.findElement(By.id("txt-search")).clear();
    driver.findElement(By.id("txt-search")).sendKeys("boston university");
    driver.findElement(By.id("btn-search")).click();
    Thread.sleep(4000); 
    driver.findElement(By.cssSelector("span.ng-binding")).click();
    Thread.sleep(4000); 
    driver.findElement(By.xpath("//div[@id='account_info']/div/div[2]/button[2]")).click();
    Thread.sleep(4000); 
    new Select(driver.findElement(By.xpath("//div[@id='post_info']/form/table/tbody/tr[3]/td/div/div/select"))).selectByVisibleText("Boston");
    driver.findElement(By.xpath("//input[@type='text']")).clear();
    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("boston university");
    driver.findElement(By.cssSelector("span.input-group-btn > button.btn.btn-default")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[37]")).click();
    driver.findElement(By.xpath("//input[@type='time']")).clear();
    driver.findElement(By.xpath("//input[@type='time']")).sendKeys("10:00");
    driver.findElement(By.xpath("(//input[@type='time'])[2]")).clear();
    driver.findElement(By.xpath("(//input[@type='time'])[2]")).sendKeys("18:00");
    driver.findElement(By.name("price")).clear();
    driver.findElement(By.name("price")).sendKeys("10");
    driver.findElement(By.id("btn-submit")).click();
    Thread.sleep(4000); 
    driver.findElement(By.id("btn-search")).click();
    Thread.sleep(4000); 
    driver.findElement(By.cssSelector("span.glyphicon.glyphicon-user")).click();
    Thread.sleep(4000); 
    driver.findElement(By.xpath("//div[@id='account_info']/div/div[2]/button")).click();
    Thread.sleep(4000); 
    driver.findElement(By.linkText("¡Á")).click();
    Thread.sleep(4000); 
    driver.findElement(By.id("btn-search")).click();
    Thread.sleep(4000); 
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
