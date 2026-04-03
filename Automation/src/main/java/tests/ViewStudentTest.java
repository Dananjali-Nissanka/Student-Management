package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ViewStudentTest {

    public static void runViewStudent(WebDriver driver) throws InterruptedException {
        driver.get("file:///D:/Student-Management/Students.html");
        Thread.sleep(1000);

        // Locate the student table
        WebElement tableBody = driver.findElement(By.id("studentTableBody"));
        java.util.List<WebElement> rows = tableBody.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            java.util.List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 2) {
                String name = cells.get(1).getText().trim();
                if (name.equals("John Doe")) {
                    WebElement viewButton = row.findElement(By.cssSelector("button.btn-info"));
                    viewButton.click();
                    Thread.sleep(2000);
                    break;
                }
            }
        }

        // Wait for the new page to load and for the full name to appear in the studentDetails section
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='studentDetails']/p[2]")));
        Thread.sleep(1000);

        // Get the Full Name from the second <p> tag inside the studentDetails div and strip "Full Name: "
        String fullNameText = driver.findElement(By.xpath("//*[@id='studentDetails']/p[2]")).getText().trim();
        String fullName = fullNameText.replace("Full Name: ", "").trim();
        Thread.sleep(1000);

        // Verify if the student's full name matches the expected name
        if (fullName.equals("John Doe")) {
            System.out.println("ViewStudentTest Passed: John Doe's details are shown");
            Thread.sleep(1000);
        } else {
            System.out.println("ViewStudentTest Failed: Expected 'John Doe' but found '" + fullName + "'");
            Thread.sleep(1000);
        }

        // After verifying the full name, click the Edit button
        WebElement editButton = driver.findElement(By.id("editBtn"));
        editButton.click();
        Thread.sleep(1000);

        // Wait for the Edit page to load and check for EditStudent.html in the URL
        wait.until(ExpectedConditions.urlContains("EditStudent.html"));
        System.out.println("Successfully navigated to the edit page.");
        Thread.sleep(1000);
    }
}
