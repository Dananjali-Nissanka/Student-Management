package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class DeleteStudentTest {

    public static void runDeleteStudentTest(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Ensure you're already on Students.html
            wait.until(ExpectedConditions.urlContains("Students.html"));
            Thread.sleep(1000);
            System.out.println("On Students.html page");
            Thread.sleep(1000);

            // ====== First Test: CANCEL DELETE for second student ======
            WebElement studentNameBefore = driver.findElement(By.xpath("//*[@id=\"studentTableBody\"]/tr[2]/td[2]"));
            String nameBefore = studentNameBefore.getText();

            WebElement cancelDeleteIcon = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id='studentTableBody']/tr[2]/td[7]/button[3]/i")));
            Thread.sleep(2000);
            cancelDeleteIcon.click();
            System.out.println("Clicked delete icon for second student (cancel case)");
            Thread.sleep(1000);

            wait.until(ExpectedConditions.alertIsPresent());
            Alert cancelAlert = driver.switchTo().alert();
            System.out.println("Cancel Alert text: " + cancelAlert.getText());
            Thread.sleep(1000);
            cancelAlert.dismiss(); // Click 'Cancel'
            System.out.println("Cancelled deletion for second student");
            Thread.sleep(1000);

            WebElement studentNameAfter = driver.findElement(By.xpath("//*[@id='studentTableBody']/tr[2]/td[2]"));
            String nameAfter = studentNameAfter.getText();

            if (nameBefore.equals(nameAfter)) {
                System.out.println("Cancel Delete Test Passed: Student still present");
            } else {
                System.out.println("Cancel Delete Test Failed: Student was deleted unexpectedly");
            }

            // ====== Second Test: DELETE first student ======
            WebElement deleteIcon = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id='studentTableBody']/tr[1]/td[7]/button[3]/i")));
            Thread.sleep(2000);
            deleteIcon.click();
            System.out.println("Clicked delete icon for first student");
            Thread.sleep(1000);

            wait.until(ExpectedConditions.alertIsPresent());
            Alert deleteAlert = driver.switchTo().alert();
            System.out.println("Delete Alert text: " + deleteAlert.getText());
            Thread.sleep(1000);
            deleteAlert.accept(); // Click 'Yes'
            Thread.sleep(1000);
            System.out.println("Confirmed deletion of first student");

            Thread.sleep(1000); // Optional short wait

        } catch (Exception e) {
            System.out.println("DeleteStudentTest Failed: " + e.getMessage());
        }
    }
}
