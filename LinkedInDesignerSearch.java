import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileWriter;
import java.time.Duration;
import java.util.List;

public class LinkedInDesignerSearch {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Secure login: use environment variables
        String email = System.getenv("LINKEDIN_EMAIL");
        String password = System.getenv("LINKEDIN_PASSWORD");

        if (email == null || password == null) {
            System.err.println("❌ Please set LINKEDIN_EMAIL and LINKEDIN_PASSWORD environment variables.");
            driver.quit();
            return;
        }

        try {
            // Step 1: Login
            driver.get("https://www.linkedin.com/login");

            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            WebElement passwordInput = driver.findElement(By.id("password"));
            WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));

            emailInput.sendKeys(email);
            passwordInput.sendKeys(password);
            loginBtn.click();

            // Step 2: Wait for login to complete
            wait.until(ExpectedConditions.urlContains("/feed"));

            // Step 3: Navigate to search for keyword "Designer"
            driver.get("https://www.linkedin.com/search/results/people/?keywords=Designer");

            // Step 4: Wait for results and collect names
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.cssSelector("span.entity-result__title-text > a > span[aria-hidden='true']")
            ));

            List<WebElement> names = driver.findElements(
                    By.cssSelector("span.entity-result__title-text > a > span[aria-hidden='true']")
            );

            try (FileWriter writer = new FileWriter("designer_search_results.csv")) {
                writer.write("Name\n");
                for (WebElement person : names) {
                    String name = person.getText().trim();
                    System.out.println("👤 " + name);
                    writer.write(name + "\n");
                }
            }

            // Optional: Screenshot
            File screenshot = ((org.openqa.selenium.TakesScreenshot) driver)
                    .getScreenshotAs(org.openqa.selenium.OutputType.FILE);
            File output = new File("designer_search_screenshot.png");
            screenshot.renameTo(output);
            System.out.println("📸 Screenshot saved to: " + output.getAbsolutePath());

            System.out.println("✅ Designer search completed. Results saved to designer_search_results.csv");

        } catch (Exception e) {
            System.err.println("⚠️ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}