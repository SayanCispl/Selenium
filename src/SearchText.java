import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchText {
    public static void main(String[] args)
    {
        System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\chromedriver-win64\\chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //search for text on website
        String url = "https://toptenbestsoftware.org/";
        driver.manage().window().maximize();
        //Opening the URL
        driver.get(url);
        //Getting current URL source code
        String get_source = driver.getPageSource();
        //Text you want to search
        String search_text = "Internet Tools";
        // print True if text is present else False
        System.out.println(get_source.contains(search_text));

        driver.quit();
        
    }
}
