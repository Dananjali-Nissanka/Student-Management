package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class EditStudentTest {

    public static void runEditStudentTest(WebDriver driver) {
        try {
            // Open ViewStudent.html
            driver.get("file:///D:/Student-Management/ViewStudent.html");
            Thread.sleep(1000);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Click on the Edit button (with id="editBtn")
            WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("editBtn")));
            editButton.click();
            Thread.sleep(1000);

            // Wait for EditStudent.html to load or form fields to appear
            wait.until(ExpectedConditions.urlContains("EditStudent.html"));
            Thread.sleep(1000);

            // Wait for the form to load and then interact with the fields inside it
            WebElement updateForm = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("updateForm")));
            Thread.sleep(1000);

            // Interacting with Full Name field (using its XPath)
            WebElement fullNameField = updateForm.findElement(By.xpath("//*[@id='updateForm']/div[2]/label/following-sibling::input"));
            Thread.sleep(1000);
            WebElement ageField = updateForm.findElement(By.xpath("//*[@id='age']"));
            Thread.sleep(1000);
            WebElement classField = updateForm.findElement(By.xpath("//*[@id='className']"));
            Thread.sleep(1000);

            // Modify form fields
            ageField.clear();
            Thread.sleep(1000);
            ageField.sendKeys("16");

            classField.clear();
            Thread.sleep(1000);
            classField.sendKeys("Grade 11");
            Thread.sleep(1000);

            // Submit the form
            WebElement submitButton = updateForm.findElement(By.cssSelector("button[type='submit']"));
            submitButton.click();

            // Wait for alert to appear
            WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());

            // Verify the alert text (Ensure the correct alert message is displayed)
            String alertText = alert.getText();
            if (alertText.equals("Student Updated Successfully!")) {
                System.out.println("EditStudentTest Passed: Correct alert message received.");
                Thread.sleep(1000);
            } else {
                System.out.println("EditStudentTest Failed: Incorrect alert message.");
                Thread.sleep(1000);
            }

            // Accept the alert
            alert.accept();

            // Wait for redirection to Students.html
            WebDriverWait redirectWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            redirectWait.until(ExpectedConditions.urlContains("Students.html"));
            Thread.sleep(1000);

            if (driver.getCurrentUrl().contains("Students.html")) {
                System.out.println("EditStudentTest Passed: Successfully redirected to Students.html");
                // Add a small wait (e.g., 2 seconds) before continuing
                Thread.sleep(2000); // 2-second wait
            } else {
                System.out.println("EditStudentTest Failed: Redirection did not happen as expected.");
                Thread.sleep(1000);
            }

            // Now add the new test case for Ayesha (second row)
            System.out.println("Running EditSecondStudentTest: Editing Guardian Details for Ayesha Fernando...");
            Thread.sleep(1000);

            // Click on the Edit button for Ayesha (second row in the table)
            WebElement editSecondButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='studentTableBody']/tr[2]/td[7]/button[2]")));
            editSecondButton.click();
            Thread.sleep(1000);

            // Wait for EditStudent.html to load or form fields to appear
            wait.until(ExpectedConditions.urlContains("EditStudent.html"));

            // Wait for the form to load and then interact with the fields inside it
            WebElement secondUpdateForm = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("updateForm")));
            Thread.sleep(1000);

            // Interacting with Guardian Name and Guardian Email fields
            WebElement guardianNameField = secondUpdateForm.findElement(By.xpath("//*[@id='updateForm']/div[7]/label/following-sibling::input"));
            WebElement guardianEmailField = secondUpdateForm.findElement(By.xpath("//*[@id='updateForm']/div[8]/label/following-sibling::input"));

            // Modify form fields for guardian details
            guardianNameField.clear();
            guardianNameField.sendKeys("Amali Fernando");
            Thread.sleep(1000);

            guardianEmailField.clear();
            guardianEmailField.sendKeys("amalifernando@gmail.com");
            Thread.sleep(1000);

            // Submit the form
            WebElement secondSubmitButton = secondUpdateForm.findElement(By.cssSelector("button[type='submit']"));
            secondSubmitButton.click();
            Thread.sleep(1000);

            // Wait for alert to appear
            alert = alertWait.until(ExpectedConditions.alertIsPresent());

            // Verify the alert text (Ensure the correct alert message is displayed)
            alertText = alert.getText();
            if (alertText.equals("Student Updated Successfully!")) {
                System.out.println("EditSecondStudentTest Passed: Correct alert message received for Ayesha.");
                Thread.sleep(1000);
            } else {
                System.out.println("EditSecondStudentTest Failed: Incorrect alert message for Ayesha.");
                Thread.sleep(1000);
            }

            // Accept the alert
            alert.accept();

            // Wait for redirection to Students.html
            redirectWait.until(ExpectedConditions.urlContains("Students.html"));
            Thread.sleep(1000);

            if (driver.getCurrentUrl().contains("Students.html")) {
                System.out.println("EditSecondStudentTest Passed: Successfully redirected to Students.html");
                Thread.sleep(1000);
            } else {
                System.out.println("EditSecondStudentTest Failed: Redirection did not happen as expected.");
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            System.out.println("EditStudentTest Failed: " + e.getMessage());
        }
    }
}
