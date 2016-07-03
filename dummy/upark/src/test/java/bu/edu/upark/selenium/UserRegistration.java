package bu.edu.upark.selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class UserRegistration {
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
  public void testUserRegistration() throws Exception {
    driver.get(baseUrl + "/upark/#/");
    driver.findElement(By.cssSelector("span.ng-binding")).click();
    Thread.sleep(2000);
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("rayli9@bu.edu");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("happyrl");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000); 
    
    driver.findElement(By.linkText("register one")).click();    
    driver.findElement(By.cssSelector("div.col-md-6 > input[name=\"username\"]")).clear();
    driver.findElement(By.cssSelector("div.col-md-6 > input[name=\"username\"]")).sendKeys("rayli");    
    Thread.sleep(2000); 
    
    driver.findElement(By.cssSelector("div.col-md-6 > input[name=\"username\"]")).clear();
    driver.findElement(By.cssSelector("div.col-md-6 > input[name=\"username\"]")).sendKeys("rayli@bu.");
    Thread.sleep(2000); 
    
    driver.findElement(By.cssSelector("div.col-md-6 > input[name=\"username\"]")).clear();
    driver.findElement(By.cssSelector("div.col-md-6 > input[name=\"username\"]")).sendKeys("rayli9@bu.edu");
    Thread.sleep(2000); 
    
    driver.findElement(By.cssSelector("div.col-md-6 > input[name=\"password\"]")).clear();
    driver.findElement(By.cssSelector("div.col-md-6 > input[name=\"password\"]")).sendKeys("hap");
    Thread.sleep(2000); 
    
    driver.findElement(By.cssSelector("div.col-md-6 > input[name=\"password\"]")).clear();
    driver.findElement(By.cssSelector("div.col-md-6 > input[name=\"password\"]")).sendKeys("happyrl");
    driver.findElement(By.name("repassword")).clear();
    driver.findElement(By.name("repassword")).sendKeys("happyrl888888");
    Thread.sleep(2000);
        
    driver.findElement(By.name("firstname")).clear();
    driver.findElement(By.name("firstname")).sendKeys("Rui");
    driver.findElement(By.name("lastname")).clear();
    driver.findElement(By.name("lastname")).sendKeys("Li");   
    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
    Thread.sleep(2000); 
    
    driver.findElement(By.name("repassword")).clear();
    driver.findElement(By.name("repassword")).sendKeys("happyrl");
    Thread.sleep(2000); 
    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
    Thread.sleep(2000); 
    
    driver.findElement(By.linkText("Log Out")).click();
    Thread.sleep(2000); 
    driver.findElement(By.cssSelector("span.ng-binding")).click();
    Thread.sleep(2000); 
    driver.findElement(By.linkText("register one")).click();
    Thread.sleep(2000); 
    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
    Thread.sleep(2000); 
    driver.findElement(By.cssSelector("#modal-register > div.modal-dialog > div.modal-content > div.modal-header > button.close")).click();
    Thread.sleep(2000); 
    driver.findElement(By.cssSelector("span.glyphicon.glyphicon-user")).click();
    Thread.sleep(2000); 
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000); 
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
