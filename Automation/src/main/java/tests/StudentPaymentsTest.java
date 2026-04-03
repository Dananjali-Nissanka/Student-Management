package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.ArrayList;
import java.io.File;
import org.openqa.selenium.io.FileHandler;

public class StudentPaymentsTest {

    public static void runStudentPaymentsTest(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Click on Payment Details button
            WebElement paymentDetailsBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div/div/div[2]/div/div[2]/a[2]")));
            paymentDetailsBtn.click();
            Thread.sleep(1000);

            // Wait for StudentPayments.html to load
            wait.until(ExpectedConditions.urlContains("StudentPayments.html"));
            System.out.println("Navigated to StudentPayments.html");
            Thread.sleep(1000);

            // Select student from dropdown
            WebElement studentDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("studentSelect")));
            Select select = new Select(studentDropdown);
            select.selectByIndex(1); // Ayesha is index 1
            System.out.println("Selected student: Ayesha");
            Thread.sleep(1000);

            // Click Calculate Total button
            WebElement calcBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div/div/div[2]/div/div/div[3]/button[1]")));
            calcBtn.click();
            System.out.println("Clicked Calculate Total");
            Thread.sleep(1000);

            // Click Generate Receipt button
            WebElement genReceiptBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div/div/div[2]/div/div/div[3]/button[2]")));
            genReceiptBtn.click();
            System.out.println("Clicked Generate Receipt");
            Thread.sleep(1000);

            // Switch to new tab if opened
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            if (tabs.size() > 1) {
                driver.switchTo().window(tabs.get(1)); // Switch to receipt tab
                System.out.println("Receipt page opened: " + driver.getCurrentUrl());

                // Wait for content and button to load
                Thread.sleep(2000);

                // Click the "Download Receipt" button (modified receipt page)
                WebElement downloadBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Download Receipt')]")));
                downloadBtn.click();
                System.out.println("Clicked 'Download Receipt' button");

                // Optional: wait for the download to complete
                Thread.sleep(3000); // Adjust delay if needed

                // Optional screenshot
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileHandler.copy(screenshot, new File("receipt_screenshot.png"));
                System.out.println("Screenshot saved as receipt_screenshot.png");

                // Close the receipt tab
                driver.close();
                driver.switchTo().window(tabs.get(0));
            } else {
                System.out.println("Receipt did not open in a new tab. Skipping download.");
                Thread.sleep(1000);
            }

            // Navigate back to Students.html
            driver.get("file:///D:/StudentManagement/Students.html");
            wait.until(ExpectedConditions.urlContains("Students.html"));
            System.out.println("Back to Students.html");
            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println("StudentPaymentsTest Failed: " + e.getMessage());

        }
    }
}
