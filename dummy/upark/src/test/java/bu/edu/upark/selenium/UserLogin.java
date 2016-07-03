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

/**
 * Test case to test user login function.
 * @author Feiyu Shi
 * @since Nov. 21, 2014
 */
public class UserLogin {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @Test
  public void testUserLoginCorrect() throws Exception {
    driver.get(baseUrl + "/upark/#/");
    driver.findElement(By.cssSelector("span.ng-binding")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("fshi@bu.edu");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + "Incorrect username or password" + "')]"));
	assertFalse(list.size() > 0);
  }
  
  @Test
  public void testUserLoginWrongPassword() throws Exception {
	  driver.get(baseUrl + "/upark/#/");
	  driver.findElement(By.cssSelector("span.ng-binding")).click();
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys("fshi@bu.edu");
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys("ad");
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  // check if "Incorrect username or password" is displayed
	  List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + "Incorrect username or password" + "')]"));
	  assertTrue(list.size() > 0);
  }

  @Test
  public void testUserLoginWrongUserName() throws Exception {
	  driver.get(baseUrl + "/upark/#/");
	  driver.findElement(By.cssSelector("span.ng-binding")).click();
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys("fshi2@bu.edu");
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys("admin");
	  driver.findElement(By.xpath("//button[@type='submit']")).click();

	  List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + "Incorrect username or password" + "')]"));
	  assertTrue(list.size() > 0);
  }
  
  @Test
  public void testUserLoginWrongUserNameANDPassword() throws Exception {
	  driver.get(baseUrl + "/upark/#/");
	  driver.findElement(By.cssSelector("span.ng-binding")).click();
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys("fshi2@bu.edu");
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys("adm");
	  driver.findElement(By.xpath("//button[@type='submit']")).click();

	  List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + "Incorrect username or password" + "')]"));
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
