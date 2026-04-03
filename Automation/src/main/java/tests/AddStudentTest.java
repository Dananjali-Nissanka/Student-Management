package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AddStudentTest {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            runTestCase1(driver); // John Doe
            runValidationTestCase(driver); // Empty fields
            runTestCase2(driver); // Ayesha Fernando

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    // Utility method to load Students.html and click the Add Student link
    public static void navigateToAddStudent(WebDriver driver) throws InterruptedException {
        driver.get("file:///D:/Student-Management/Students.html");
        Thread.sleep(1000);
        WebElement addStudentBtn = driver.findElement(By.linkText("Add Student"));
        addStudentBtn.click();
        Thread.sleep(1000);
    }

    // Test Case 1: Add John Doe
    public static void runTestCase1(WebDriver driver) throws InterruptedException {
        navigateToAddStudent(driver);

        driver.findElement(By.id("fullName")).sendKeys("John Doe");
        Thread.sleep(1000);
        driver.findElement(By.id("age")).sendKeys("15");
        Thread.sleep(1000);
        new Select(driver.findElement(By.id("gender"))).selectByVisibleText("Male");
        Thread.sleep(1000);
        driver.findElement(By.id("className")).sendKeys("Grade 8 - B");
        Thread.sleep(1000);

        driver.findElement(By.id("selectedModules")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[value='Maths']")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[value='Science']")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("guardianName")).sendKeys("Jane Doe");
        Thread.sleep(1000);
        driver.findElement(By.id("guardianEmail")).sendKeys("jane.doe@gmail.com");
        Thread.sleep(1000);
        driver.findElement(By.id("emergencyContact")).sendKeys("0771234567");
        Thread.sleep(1000);
        driver.findElement(By.id("address")).sendKeys("123 Test Street, City");
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(1000);

        Alert alert = driver.switchTo().alert();
        System.out.println("Test Case 1 Alert: " + alert.getText());
        alert.accept();
    }


    // Test Case 2: Submit with missing fields to show validation
    public static void runValidationTestCase(WebDriver driver) throws InterruptedException {
        navigateToAddStudent(driver);

        // Leave required fields blank
        driver.findElement(By.id("fullName")).sendKeys(""); // Empty field
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(1000);

        WebElement fullName = driver.findElement(By.id("fullName"));
        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", fullName);
        System.out.println("Test Case 3 Validation Message: " + validationMessage);
    }

    // Test Case 3: Add Ayesha Fernando
    public static void runTestCase2(WebDriver driver) throws InterruptedException {
        navigateToAddStudent(driver);

        driver.findElement(By.id("fullName")).sendKeys("Ayesha Fernando");
        Thread.sleep(1000);
        driver.findElement(By.id("age")).sendKeys("14");
        Thread.sleep(1000);
        new Select(driver.findElement(By.id("gender"))).selectByVisibleText("Female");
        Thread.sleep(1000);
        driver.findElement(By.id("className")).sendKeys("Grade 8 - C");
        Thread.sleep(1000);

        driver.findElement(By.id("selectedModules")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[value='ICT']")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[value='Science']")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("guardianName")).sendKeys("Nuwan Fernando");
        Thread.sleep(1000);
        driver.findElement(By.id("guardianEmail")).sendKeys("nuwan@gmail.com");
        Thread.sleep(1000);
        driver.findElement(By.id("emergencyContact")).sendKeys("0761234567");
        Thread.sleep(1000);
        driver.findElement(By.id("address")).sendKeys("45, Lake Road, Kandy");
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(1000);

        Alert alert = driver.switchTo().alert();
        System.out.println("Test Case 2 Alert: " + alert.getText());
        alert.accept();
    }

}
