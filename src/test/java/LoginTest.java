import base.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {


    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.out.println("Running Before Class");
        driver = new ChromeDriver();
        driver.get("http://localhost:63342/Login/__files/index.html?_ijt=rbehtlbvl42uju1le6qpc2vu3v&_ij_reload=RELOAD_ON_SAVE");



    }

    @Test
    public void testLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.SetLoginAndClickNext("test@devskiller.com");
        String code = loginPage.OpenCodePageAndReturnCode();
        loginPage.SetCodeAndClickNext(code);
        loginPage.FillMaskedPasswordAndClickLogin("DevSkill1!");
        loginPage.GetLoggedInText();
        driver.quit();
    }
}
