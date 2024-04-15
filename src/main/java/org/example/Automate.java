package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Automate
{
    private static WebDriver driver;

    //Store the email address
    static String email = "dhfh"+random()+"fj@gmail.com";

    //Store password
    static String password = "Dhruvesh285";

    //Requirement for confirmation of Registration
    static String expectedMessage = "Your registration completed";

    //Click on element method
    public static void clickOnElement(By by)
    {
        driver.findElement(by).click();
    }

    //Write text inside text box method
    public static void writeText(By by, String text)
    {
        driver.findElement(by).sendKeys(text);
    }

    //Capture text of the element method
    public static String captureTextOfTheElement(By by)
    {
        return driver.findElement(by).getText();
    }

    //Open the browser
    public static void openTheBrowser()
    {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
    }

    //Close the browser
    public static void closeTheBrowser()
    {
        driver.quit();
    }

    //Method to generate Random String
    public static String random()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyyddmmhms");
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Test
    public static void userShouldBeAbleToRegisterSuccessfully()
    {
        //Open the browser, maximize & type the URL
        openTheBrowser();

        //Click on the Register Button
        clickOnElement(By.className("ico-register"));

        //Entre the First name
        writeText(By.id("FirstName"), "UniqueTesting");

        //Entre the Last name
        writeText(By.id("LastName"), "Test123");

        //Entre the Email ID
        writeText(By.id("Email"), email);

        //Entre the password
        writeText(By.id("Password"), password);

        //Entre the confirmation password
        writeText(By.id("ConfirmPassword"), password);

        //Click on the Register button
        clickOnElement(By.id("register-button"));

        //Checking Registration successfully done then correct message display or not
        String actulMessage = captureTextOfTheElement(By.className("result"));
        Assert.assertEquals(actulMessage,expectedMessage, "Yor are not Register successfully");

        //Not closing browser because I am going to use this method for other methods
    }

    @Test
    public static void registerUserShouldableToLoginSuccessfully()
    {
        //Register the user
        userShouldBeAbleToRegisterSuccessfully();

        //Click on the login button
        clickOnElement(By.className("ico-login"));

        //Entre the email
        writeText(By.id("Email"), email);

        //Entre the password
        writeText(By.className("password"), password);

        //Click on Login button
        clickOnElement(By.xpath("//button[normalize-space()='Log in']"));

        //Not closing browser because I am going to use this method for other methods
    }

    @Test
    public static void userShouldbeAbletoReferProductToFriendByEmail()
    {

        registerUserShouldableToLoginSuccessfully();

        //Click on Electronics option from top bar
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Electronics']"));

        //Click on Camera & photo option
        clickOnElement(By.xpath("//a[@title='Show products in category Camera & photo'][normalize-space()='Camera & photo']"));

        //Click on Leica T Mirrorless Digital Camera
        clickOnElement(By.xpath("//a[normalize-space()='Leica T Mirrorless Digital Camera']"));

        //Click on email friend
        clickOnElement(By.xpath("//button[normalize-space()='Email a friend']"));

        //Entre the friend email
        writeText(By.xpath("//input[@id='FriendEmail']"), "ddss@gmail.com");

        //Click on SEND EMAIl button
        clickOnElement(By.xpath("//button[normalize-space()='Send email']"));

        //Close the browser
        closeTheBrowser();
    }

    @Test
    public static void addToCartbuttonisthereornot()
    {
        registerUserShouldableToLoginSuccessfully();

        //Click on Electronics option from top bar
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Electronics']"));

        //Click on Camera & photo
        clickOnElement(By.xpath("//a[@title='Show products in category Camera & photo'][normalize-space()='Camera & photo']"));

        //Storing text of add to cart button
        String addcart1 = captureTextOfTheElement(By.xpath("//div[@class='item-grid']//div[1]//div[1]//div[2]//div[3]//div[2]//button[2]"));

        //Storing text of add to cart button
        String addcart2 = captureTextOfTheElement(By.xpath("//div[@class='center-2']//div[3]//div[1]//div[2]//div[3]//div[2]//button[1]"));

        //Display Text of add to cart button
        System.out.println(addcart1);
        System.out.println(addcart2);

        //Close the browser
        closeTheBrowser();
    }

}
