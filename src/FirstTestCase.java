// Java program to detect broken
// links on the webpage
// Imported Packages
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class FirstTestCase {
    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\chromedriver-win64\\chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://toptenbestsoftware.org/");
        driver.manage().window().maximize();
        // Getting All The Links In One List
        List <WebElement>links =
                driver.findElements(By.tagName("a"));

        // Printing The Total Links Number
        System.out.println("Total Link Size: " + links.size());
        for (WebElement element : links) {
            // Checking Each & Every Links
            String url = element.getAttribute("href");

            URL link = new URL(url);
            HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
            httpConn.connect();

            // Getting The Response Code
            int code = httpConn.getResponseCode();

            // If The Number Is Greater Than 400,
            // Then It Is Broken Link
            if (code >= 400) {
                System.out.println("Broken Link: " + url);
            } else {
                System.out.println("Valid Link: " + url);
            }
        }

        System.out.println();
        System.out.println("All Links Checked");

        // Closing The Driver
        driver.quit();
    }
}


