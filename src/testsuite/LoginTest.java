package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utility.Utility;

public class LoginTest extends Utility {

    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setBaseUrl() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        sendTextToElement(By.id("username"), "tomsmith");
        sendTextToElement(By.name("password"), "SuperSecretPassword!");
        clickOnElement(By.xpath("//button[@class='radius']"));
        String expectedText = "Secure Area";
        String actualText = getTextFromElement(By.xpath("//div[@id=\"content\"]/div/h2"));
        Assert.assertEquals(expectedText, actualText);
    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        sendTextToElement(By.id("username"), "tomsmith1");
        sendTextToElement(By.name("password"), "SuperSecretPassword!");
        clickOnElement(By.xpath("//button[@class='radius']"));
        String expectedMessage = "Your username is invalid!\n" + "×";
        String actualMessage = getTextFromElement(By.xpath("//div[@id='flash-messages']/div[1]"));
        Assert.assertEquals(expectedMessage, actualMessage);

    }

    @Test
    public void verifyThePasswordErrorMessage() {
        sendTextToElement(By.id("username"), "tomsmith");
        sendTextToElement(By.name("password"), "SuperSecretPassword");
        clickOnElement(By.xpath("//button[@class='radius']"));
        String expectedMessage = "Your password is invalid!\n" + "×";
        String actualMessage = getTextFromElement(By.xpath("//div[@id='flash-messages']//div[1]"));
        Assert.assertEquals(expectedMessage, actualMessage);
    }
    @After
    public void tearDown(){
        closeBrowser();
    }


}
