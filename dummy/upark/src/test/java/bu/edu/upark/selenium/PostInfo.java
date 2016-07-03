package bu.edu.upark.selenium;

import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class PostInfo {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test
  public void testPostInfoCorrect() throws Exception {
    driver.get(baseUrl + "/upark/#/");
    driver.findElement(By.linkText("Sign In")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("fshi@bu.edu");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.cssSelector("span.ng-binding")).click();
    driver.findElement(By.xpath("//div[@id='account_info']/div/div[2]/button[2]")).click();
    new Select(driver.findElement(By.xpath("//div[@id='post_info']/form/table/tbody/tr[3]/td/div/div/select"))).selectByVisibleText("Boston");
    driver.findElement(By.xpath("//input[@type='text']")).clear();
    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("85 Van Ness St");
    driver.findElement(By.cssSelector("span.input-group-btn > button.btn.btn-default")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
    driver.findElement(By.xpath("//input[@type='time']")).clear();
    driver.findElement(By.xpath("//input[@type='time']")).sendKeys("08:00");
    driver.findElement(By.xpath("(//input[@type='time'])[2]")).clear();
    driver.findElement(By.xpath("(//input[@type='time'])[2]")).sendKeys("20:00");
    driver.findElement(By.name("price")).clear();
    driver.findElement(By.name("price")).sendKeys("10");
    driver.findElement(By.id("btn-submit")).click();
    driver.findElement(By.xpath("//li[3]/a/span[2]")).click();
  }

  @Test
  public void testPostInfoWrongPrice1() throws Exception {
    driver.get(baseUrl + "/upark/#/");
    driver.findElement(By.linkText("Sign In")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("fshi@bu.edu");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.cssSelector("span.ng-binding")).click();
    driver.findElement(By.xpath("//div[@id='account_info']/div/div[2]/button[2]")).click();
    new Select(driver.findElement(By.xpath("//div[@id='post_info']/form/table/tbody/tr[3]/td/div/div/select"))).selectByVisibleText("Boston");
    driver.findElement(By.xpath("//input[@type='text']")).clear();
    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("86 Van Ness St");
    driver.findElement(By.cssSelector("span.input-group-btn > button.btn.btn-default")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
    driver.findElement(By.xpath("//input[@type='time']")).clear();
    driver.findElement(By.xpath("//input[@type='time']")).sendKeys("08:00");
    driver.findElement(By.xpath("(//input[@type='time'])[2]")).clear();
    driver.findElement(By.xpath("(//input[@type='time'])[2]")).sendKeys("20:00");
    driver.findElement(By.name("price")).clear();
    driver.findElement(By.name("price")).sendKeys("-10");
    driver.findElement(By.id("btn-submit")).click();
    driver.findElement(By.xpath("//li[3]/a/span[2]")).click();
    // check if "Invalid Price" is displayed
 	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + "Invalid price" + "')]"));
 	assertTrue(list.size() > 0);
  }
  
  @Test
  public void testPostInfoWrongTimeSlot() throws Exception {
    driver.get(baseUrl + "/upark/#/");
    driver.findElement(By.linkText("Sign In")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("fshi@bu.edu");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.cssSelector("span.ng-binding")).click();
    driver.findElement(By.xpath("//div[@id='account_info']/div/div[2]/button[2]")).click();
    new Select(driver.findElement(By.xpath("//div[@id='post_info']/form/table/tbody/tr[3]/td/div/div/select"))).selectByVisibleText("Boston");
    driver.findElement(By.xpath("//input[@type='text']")).clear();
    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("18 Medfield St");
    driver.findElement(By.cssSelector("span.input-group-btn > button.btn.btn-default")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
    driver.findElement(By.xpath("//input[@type='time']")).clear();
    driver.findElement(By.xpath("//input[@type='time']")).sendKeys("18:00");
    driver.findElement(By.xpath("(//input[@type='time'])[2]")).clear();
    driver.findElement(By.xpath("(//input[@type='time'])[2]")).sendKeys("14:00");
//    driver.findElement(By.name("price")).clear();
//    driver.findElement(By.name("price")).sendKeys("10");
//    driver.findElement(By.id("btn-submit")).click();
//    driver.findElement(By.xpath("//li[3]/a/span[2]")).click();
    // check if "End time should be later than start time." is displayed
 	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + "End time should be later than start time." + "')]"));
 	assertTrue(list.size() > 0);
  }

  @Test
  public void testPostInfoWrongPrice2() throws Exception {
    driver.get(baseUrl + "/upark/#/");
    driver.findElement(By.linkText("Sign In")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("fshi@bu.edu");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.cssSelector("span.ng-binding")).click();
    driver.findElement(By.xpath("//div[@id='account_info']/div/div[2]/button[2]")).click();
    new Select(driver.findElement(By.xpath("//div[@id='post_info']/form/table/tbody/tr[3]/td/div/div/select"))).selectByVisibleText("Boston");
    driver.findElement(By.xpath("//input[@type='text']")).clear();
    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("86 Van Ness St");
    driver.findElement(By.cssSelector("span.input-group-btn > button.btn.btn-default")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
    driver.findElement(By.xpath("//input[@type='time']")).clear();
    driver.findElement(By.xpath("//input[@type='time']")).sendKeys("08:00");
    driver.findElement(By.xpath("(//input[@type='time'])[2]")).clear();
    driver.findElement(By.xpath("(//input[@type='time'])[2]")).sendKeys("20:00");
    driver.findElement(By.name("price")).clear();
    driver.findElement(By.name("price")).sendKeys("dd");
//    driver.findElement(By.id("btn-submit")).click();
//    driver.findElement(By.xpath("//li[3]/a/span[2]")).click();
    // check if "Number only" is displayed
 	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + "Number only" + "')]"));
 	assertTrue(list.size() > 0);
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
