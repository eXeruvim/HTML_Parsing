package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<Product> productList = new ArrayList<>();
        String link =
                "https://ru.banggood.com/Wholesale-Computers-and-Office-ca-5001.html?bid=210707&from=nav";
        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(link);
        Thread.sleep(1000*2);
        Document document = Jsoup.parse(webDriver.getPageSource());
        Elements elements = document.getElementsByClass("goodlist");
        //elements.forEach(System.out::println);
        for (Element element : elements) {
            Product product = new Product();
            Document doc = Jsoup.parse(element.html());
            product.setTitle(doc.getElementsByClass("title").first().text());
            productList.add(product);
        }
        productList.forEach(System.out::println);
    }
}
