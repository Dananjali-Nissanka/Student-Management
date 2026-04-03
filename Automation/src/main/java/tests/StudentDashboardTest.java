package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class StudentDashboardTest {

    public static void runStudentDashboardTest(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Navigate to Dashboard.html
            driver.get("file:///D:/Student-Management/Dashboard.html");
            driver.manage().window().maximize();
            wait.until(ExpectedConditions.urlContains("Dashboard.html"));
            System.out.println("Dashboard page loaded");
            Thread.sleep(1000);

            // Click the "Student" link from the sidebar
            WebElement studentNavLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div/div/div[1]/nav[2]/a[2]")));
            studentNavLink.click();
            System.out.println("Clicked 'Student' link in sidebar");
            Thread.sleep(1000);

            // Wait for Students.html to load
            wait.until(ExpectedConditions.urlContains("Students.html"));
            System.out.println("Navigated to Students.html from Dashboard");
            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println("StudentDashboardTest Failed: " + e.getMessage());
        }
    }
}
