package org.example;

import tests.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            System.out.println("Running StudentDashboardTest...");
            StudentDashboardTest.runStudentDashboardTest(driver);


            // Run all test cases from AddStudentTest
            System.out.println("Running AddStudentTest: Adding John Doe, Validation Case, Ayesha Fernando...");
            AddStudentTest.runTestCase1(driver);           // Add John Doe
            AddStudentTest.runValidationTestCase(driver);  // Validate empty field
            AddStudentTest.runTestCase2(driver);           // Add Ayesha Fernando

            // Run View Student test for John Doe
            System.out.println("Running ViewStudentTest...");
            ViewStudentTest.runViewStudent(driver);

            //  Run Edit Student test
            System.out.println("Running EditStudentTest...");
            EditStudentTest.runEditStudentTest(driver);

            // Run the SearchStudentTest
            System.out.println("Running SearchStudentTest: Searching for 'John'...");
            SearchStudentTest.runSearchStudentTest(driver); // Calling the SearchStudentTest

            System.out.println("Running StudentPaymentsTest...");
            StudentPaymentsTest.runStudentPaymentsTest(driver);

            System.out.println("Running DeleteStudentTest...");
            DeleteStudentTest.runDeleteStudentTest(driver);



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
