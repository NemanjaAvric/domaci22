import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Domaci22 {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver-driver-chromedriver", "C:\\Nova fascikla\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://vue-demo.daniel-avellaneda.com/");
        driver.manage().window().fullscreen();
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/header/div/div[3]/a[3]"));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        loginButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void verifyLoginPagesUrl() {
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://vue-demo.daniel-avellaneda.com/login";
        Assert.assertEquals(actualURL, expectedURL);
        WebElement emailInscription = driver.findElement(By.xpath("//*[@id=\"app\"]/div/main/div/div[2]/div/div/div[3]/span/form/div/div[1]/span/div/div/div[1]/div/label"));
        String actualEmailInscritpion = emailInscription.getText();
        String expectedEmailInscription = "E-mail";
        Assert.assertEquals(actualEmailInscritpion, expectedEmailInscription);
        WebElement passwordInscription = driver.findElement(By.xpath("//*[@id=\"app\"]/div/main/div/div[2]/div/div/div[3]/span/form/div/div[2]/span/div/div/div[1]/div/label"));
        String actualPasswordInscription = passwordInscription.getText();
        String expectedPasswordInscription = "Password";
        Assert.assertEquals(actualPasswordInscription, expectedPasswordInscription);
    }

    @Test
    public void testUDNEMessage() {
        Faker faker = new Faker();
        WebElement emailInputTextField = driver.findElement(By.id("email"));
        emailInputTextField.sendKeys(faker.internet().emailAddress());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement passwordInputTextField = driver.findElement(By.id("password"));
        passwordInputTextField.sendKeys(faker.internet().password());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div/main/div/div[2]/div/div/div[3]/span/form/div/div[3]/button"));
        loginButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement userDoesNotExistInscription = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]/ul/li"));
        String actualUDNEMessage = userDoesNotExistInscription.getText();
        String expectedUDNEMessage = "User does not exists";
        Assert.assertEquals(actualUDNEMessage, expectedUDNEMessage);
    }

    @Test
    public void verifyWrongPasswordMessage() {
        Faker faker = new Faker();
        WebElement emailInputTextField = driver.findElement(By.id("email"));
        emailInputTextField.sendKeys("admin@admin.com");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement passwordInputTextField = driver.findElement(By.id("password"));
        passwordInputTextField.sendKeys(faker.internet().password());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div/main/div/div[2]/div/div/div[3]/span/form/div/div[3]/button"));
        loginButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement wrongPassword = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]/ul/li"));
        String actualWPMessage = wrongPassword.getText();
        String expectedWPMessage = "Wrong password";
        Assert.assertEquals(actualWPMessage, expectedWPMessage);
    }

    @AfterClass
    public void afterClass() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }
}
