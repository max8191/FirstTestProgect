import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
public class first {
    Faker faker = new Faker();
    String userName = faker.name().username();
    String password = faker.internet().password();
    String nameF = faker.name().firstName();
    String nameL = faker.name().lastName();
    String email = ""+(char)(65+Math.random()*20)+(char)(65+Math.random()*20)+(char)(65+Math.random()*20)+(char)(65+Math.random()*20)+"@gmail.com";

@Test
    public void signUp() throws InterruptedException {
        WebDriver wb = new ChromeDriver();
        wb.navigate().to("http://duotify.us-east-2.elasticbeanstalk.com/register.php");
        Assert.assertEquals(wb.getTitle(),"Welcome to Duotify!","title Wrong");
        wb.findElement(By.id("hideLogin")).click();
        wb.findElement(By.id("username")).sendKeys(userName);
        wb.findElement(By.id("firstName")).sendKeys(nameF);
        wb.findElement(By.id("lastName")).sendKeys(nameL);
        wb.findElement(By.id("email")).sendKeys(email);
        wb.findElement(By.id("email2")).sendKeys(email);
        wb.findElement(By.id("password")).sendKeys(password);
        wb.findElement(By.id("password2")).sendKeys(password);
        wb.findElement(By.name("registerButton")).click();
        Assert.assertEquals(wb.getCurrentUrl(),"http://duotify.us-east-2.elasticbeanstalk.com/browse.php?","wrong link 1");
        Assert.assertEquals(wb.findElement(By.id("nameFirstAndLast")).getText(),nameF+" "+nameL,"navigate bar name");
        wb.findElement(By.id("nameFirstAndLast")).click();
        Thread.sleep(1000);
        Assert.assertTrue(wb.findElement(By.className("userInfo")).getText().contains(nameF + " " + nameL),"after click name");
        wb.findElement(By.id("rafael")).click();
        Thread.sleep(1000);
        Assert.assertEquals(wb.getCurrentUrl(),"http://duotify.us-east-2.elasticbeanstalk.com/register.php","wrong link 2");
        wb.findElement(By.id("loginUsername")).sendKeys(userName);
        wb.findElement(By.id("loginPassword")).sendKeys(password);
        wb.findElement(By.name("loginButton")).click();
        Thread.sleep(1000);
        Assert.assertTrue(wb.getPageSource().contains("You Might Also Like"));
        wb.findElement(By.id("nameFirstAndLast")).click();
        Thread.sleep(1000);
        wb.findElement(By.id("rafael")).click();
        Thread.sleep(500);
        Assert.assertEquals(wb.getCurrentUrl(),"http://duotify.us-east-2.elasticbeanstalk.com/register.php","wrong link 3");
    }

}
