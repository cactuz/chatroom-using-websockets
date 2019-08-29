package edu.udacity.java.nano.chat;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ChatApplicationTest {
    private WebDriver driver;

    @Before
    public void LaunchBrowser() {
        System.setProperty("webdriver.chrome.driver",
                "/Users/rrdiez/Documents/devproj/udacity-java-dev-exercises/chatroom" +
                        "-project/src/test/resources/chromedriver");
        this.driver = new ChromeDriver();
    }

    @Test
    public void login() throws Exception{
        this.driver.get("http://localhost:8080/");
        WebElement inputElement = this.driver.findElement(By.id("username"));
        WebElement submitElement = this.driver.findElement(By.className("submit"));
        inputElement.sendKeys("john");
        submitElement.click();

        WebElement chatSection = driver.findElement(By.className("message-container"));
        Assert.assertEquals("john joined the chatroom.", chatSection.getText());
    }

    @Test
    public void send_message() throws Exception{
        this.driver.get("http://localhost:8080/");
        WebElement inputElement = this.driver.findElement(By.id("username"));
        WebElement submitElement = this.driver.findElement(By.className("submit"));
        inputElement.sendKeys("may");
        submitElement.click();

        WebElement count = driver.findElement(By.className("chat-num"));
        Assert.assertTrue(Integer.parseInt(count.getText()) > 0);

        WebElement message = this.driver.findElement(By.className("mdui-textfield-input"));
        WebElement send = this.driver.findElement(By.className("send-msg"));
        message.sendKeys("hello to all");
        send.click();

        WebElement chatSection = driver.findElement(By.className("message-container"));
        Assert.assertTrue(chatSection.getText().contains("hello to all"));
        Thread.sleep(2000);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
