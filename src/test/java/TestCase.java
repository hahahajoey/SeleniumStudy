import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestCase {

    public WebDriver webDriver;

    @Before
    public void setUp() {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("SeleniumConfig.properties")) {
            properties.load(inputStream);
            System.setProperty("webdriver.chrome.driver", properties.getProperty("chromedriver.path"));
        } catch (IOException e) {
            throw new RuntimeException("Fail: Chrome Driver can not be configured.");
        }

        webDriver = new ChromeDriver();
    }

    @Test
    public void testSearchTitle() throws InterruptedException {

        webDriver.navigate().to("https://www.baidu.com");
        WebElement search_input = webDriver.findElement(By.name("wd"));
        search_input.sendKeys("极客时间");
        search_input.submit();
        Thread.sleep(3000);
        Assert.assertEquals("极客时间_百度搜索", webDriver.getTitle());
        webDriver.quit();
    }
}
