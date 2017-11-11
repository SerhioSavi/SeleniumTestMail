import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test
{
    static class parameter
    {
        public static String pathToDriver = "C:\\Users\\Serhios\\Desktop\\JavaRushTasks\\geckodriver.exe";
        public static String addressPage = "https://mail.ru";
        public static String login = "login";
        public static String password = "password";
        public static String subject = "autotest";
        public static String sendTo = "test@mail.ru";
        public static String bodyText = "test";


    }
    public static void main(String[] args) {

    WebDriver driver = getFirefoxDriver();
    getPage(driver);
    findElement(driver);
    sleep(10000);
    sendMail(driver);
    sleep(4000);
    logOut(driver);

}





    public static WebDriver getFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", parameter.pathToDriver);
        WebDriver driver = new FirefoxDriver();
        return driver;
    }


    public static void sleep(int msek) {
        try {
            Thread.sleep(msek);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void getPage(WebDriver driver) {
        driver.get(parameter.addressPage);
    }

    public static void findElement(WebDriver driver) {
        WebElement eLoginField = driver.findElement(By.id("mailbox:login"));
        eLoginField.sendKeys(parameter.login);
        WebElement ePasswordField = driver.findElement(By.id("mailbox:password"));
        ePasswordField.sendKeys(parameter.password);
        ePasswordField.submit();
    }

    public static void sendMail(WebDriver driver) {
        WebElement eWriteLetter = driver.findElement(By.xpath("//*[@id=\"b-toolbar__left\"]/div/div/div[2]/div/a"));
        eWriteLetter.click();
        sleep(2000);
        WebElement eToField = driver.findElement(By.xpath(
                "/html/body/div[2]/div/div[5]/div/div/div/div/div/div/div/div/div/div/div/div/div/div[6]/div[2]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div[2]/div/div/div[2]/div/div/div/textarea[2]"));
        eToField.sendKeys(parameter.sendTo);
        WebElement eSubjectField = driver.findElement(By.name("Subject"));
        eSubjectField.sendKeys(parameter.subject);
        WebElement frame = driver.findElement(By.xpath("//iframe[contains(@id,'composeEditor_ifr')]"));
        driver.switchTo().frame(frame);
        WebElement eMessageBody = driver.findElement(By.id("tinymce"));
        eMessageBody.sendKeys(parameter.bodyText);
        driver.switchTo().defaultContent();
        WebElement eSendButton = driver
                .findElement(By.xpath("//*[@id=\"b-toolbar__right\"]/div[3]/div/div[2]/div[1]/div"));
        eSendButton.click();

    }

    public static void logOut(WebDriver driver) {
        driver.findElement(By.id("PH_logoutLink")).click();
    }
}
