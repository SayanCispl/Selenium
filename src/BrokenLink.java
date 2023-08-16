import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BrokenLink {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\chromedriver-win64\\chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://santorinistyles.com/products/the-gold-phoenix-one-piece-bikini-bathing-suit?variant=44876838207802");
        driver.manage().window().maximize();
        // Getting All The Links In One List
        List<WebElement> links = driver.findElements(By.tagName("a"));
        // Printing The Total Links Number
        links.addAll(driver.findElements(By.tagName("img")));
        System.out.println("Total Link Size: " + links.size());
        List<String> urlList = new ArrayList<String>();
        for(WebElement e : links) {
            String url = e.getAttribute("href");
            urlList.add(url);
            //checkBrokenLink(url);
        }
        Thread.sleep(3000);
        long startTime= System.currentTimeMillis();
        urlList.parallelStream().forEach(e-> checkBrokenLink(e));
        long endTime= System.currentTimeMillis();

        System.out.println("Total time taken:" + (endTime-startTime) );


        driver.quit();

    }
    public static void checkBrokenLink(String LinkUrl){
        try{
            URL link = new URL(LinkUrl);
            HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
            httpConn.setConnectTimeout(5000);
            httpConn.connect();
            if(httpConn.getResponseCode()>=400){
                System.out.println("Broken Link: " + LinkUrl +httpConn.getResponseMessage());
            }
            else{
                System.out.println("Valid Link: " + LinkUrl +httpConn.getResponseMessage());
            }

        }
        catch(Exception e){

        }

    }
}