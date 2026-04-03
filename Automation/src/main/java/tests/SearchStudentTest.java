package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class SearchStudentTest {

    public static void runSearchStudentTest(WebDriver driver) {
        try {
            // Open Students.html
            driver.get("file:///D:/Student-Management/Students.html");
            Thread.sleep(1000);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Add a small wait (e.g., 2 seconds) before starting the search
            Thread.sleep(2000); // 2-second wait

            // Locate the search input field
            WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='searchInput']")));

            // Clear any existing text and type "John" in the search bar
            searchInput.clear();
            searchInput.sendKeys("John");
            Thread.sleep(1000);

            // Optionally, press the Enter key to trigger the search
            searchInput.sendKeys(Keys.RETURN);

            // Wait for search results to appear
            WebDriverWait searchWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            searchWait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id='studentTableBody']"), "John"));
            Thread.sleep(2000);


            System.out.println("SearchStudentTest Passed: Search results for 'John' displayed.");

            // Navigate back to Students.html
            driver.get("file:///D:/StudentManagement/Students.html");
            wait.until(ExpectedConditions.urlContains("Students.html"));
            System.out.println("Back to Students.html");
            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println("SearchStudentTest Failed: " + e.getMessage());
        }
    }
}
