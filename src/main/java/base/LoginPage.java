package base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LoginPage {

    WebDriver driver;
    String code;
    public LoginPage(WebDriver driver) throws InterruptedException {
        this.driver = driver;
    }

    private By txtEmail = By.xpath("//input[@type=\"email\"]");
    private By btnNext = By.xpath("(//button[text()=\"Next\"])[1]");
    private By lnkOpenPage = By.xpath("//a[text()=\"open page\"]");
    private By txtCode = By.xpath("//input[@id=\"code\"]");
    private By txtInputCode = By.xpath("//input[@type=\"number\"]");
    private By btnCodeNext = By.xpath("//button[@class=\"buttonLoginCode\"]");
   private By btnLogin = By.xpath("//button[text()=\"Log in\"]");
   private By txtLoginsuccess = By.xpath("//p[@id=\"loggedIn\"]");


    public void SetLoginAndClickNext(String login) throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(txtEmail).click();
        driver.findElement(txtEmail).sendKeys(login);
        driver.findElement(btnNext).click();
    }

    public String OpenCodePageAndReturnCode() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(lnkOpenPage).click();
        Set<String> allTabs = driver.getWindowHandles();
        ArrayList<String> tabs = new ArrayList<String>(allTabs);
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(5000);
        code = driver.findElement(txtCode).getAttribute("value");
        System.out.println("The code is"+ code);
        driver.switchTo().window(tabs.get(0));
        return code;

    }

    public void SetCodeAndClickNext(String code) throws InterruptedException {

        Thread.sleep(5000);
        driver.findElement(txtInputCode).sendKeys(code);
        Thread.sleep(5000);
        driver.findElement(btnCodeNext).click();
    }

    public void FillMaskedPasswordAndClickLogin(String password) throws InterruptedException {
        Thread.sleep(5000);
        WebElement iFramePswd = driver.findElement(By.xpath("//iFrame[@src=\"loginMasked.html\"]"));

        char[] charToSend = password.toCharArray();
        int[] fieldIndices = {0,1,2,3,4,5,6,7,8,9};
        driver.switchTo().frame(iFramePswd);
        for (int fieldNumber : fieldIndices) {

//            int charIndex = fieldNumber - 1;
//            System.out.println("The charIndex is"+ charIndex);
            char charToFill = charToSend[fieldNumber];
            WebElement passwordField = driver.findElement(By.id("passwd_"+fieldNumber));
            if (passwordField.isEnabled()) {
                passwordField.sendKeys(String.valueOf(charToFill));
            }else {
                    System.out.println("Field " + fieldNumber + " is disabled. Skipping.");
                }

//
        }

            driver.findElement(btnLogin).click();
    }

    public void GetLoggedInText(){
        System.out.println("The login successfull message is"+ driver.findElement(txtLoginsuccess).getText());
        driver.quit();
    }

}
