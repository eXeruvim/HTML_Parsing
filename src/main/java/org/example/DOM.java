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
import java.util.Objects;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class DOM {
    public static List<Product> getDOM() throws InterruptedException, IOException {
        List<Product> productList = new ArrayList<>();
        String link =
                "https://ru.banggood.com/ru/Wholesale-Computers-and-Office-ca-5001.html?bid=210707&from=nav&a=1669744527.8678&DCC=RU&currency=RUB";
        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(link);
        Thread.sleep(4000);
        Document document = Jsoup.parse(webDriver.getPageSource());
        document = Jsoup.parse(document.getElementsByClass("product-list").html());

        Document ordersDocument = Jsoup.parse(Objects.requireNonNull(document.getElementsByClass("goodlist").first()).html());
        Elements ordersElements = ordersDocument.getElementsByClass("p-wrap exclick");
        //System.out.println(ordersElements.size());
        for (Element ordersElement : ordersElements) {
            Product product = new Product();
            // Парсинг со страницы товаров
            product.setUrl(ordersElement.child(0).getElementsByClass("img notranslate").select("a").attr("href"));
            product.setSalePriceRUB(Objects.requireNonNull(ordersElement.child(1).getElementsByClass("price").first()).text());
            product.setRub(Objects.requireNonNull(ordersElement.child(2).getElementsByClass("price-old").first()).text());
            product.setTitle(ordersElement.child(3).getElementsByClass("title").select("a").attr("title"));
            product.setReviews(ordersElement.child(4).getElementsByClass("review").select("a").text());

            // Парсинг со страницы товара
            Document order = Jsoup.connect(ordersElement.child(0).getElementsByClass("img notranslate").select("a").attr("href")).get();

            product.setPicture(order.getElementsByClass("image-max").select("img").attr("src"));
            product.setRating(order.getElementsByClass("star-num js-star-num").text());
            product.setOption(Objects.requireNonNull(order.getElementsByClass("block-title").select("span").first()).text());
            product.setVariety(order.getElementsByClass("block-cnt").select("a").eachAttr("title"));
            product.setUsd(product.getRub());
            product.setSalePriceUSD(product.getSalePriceRUB());

            Thread.sleep(1000);
            productList.add(product);
        }
        return productList;
    }
}
