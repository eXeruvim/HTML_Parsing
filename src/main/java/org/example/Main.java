package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<Product> productList = new ArrayList<>();
        String link =
                "https://ru.banggood.com/ru/Wholesale-Computers-and-Office-ca-5001.html?bid=210707&from=nav&a=1669744527.8678&DCC=RU&currency=RUB";
        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(link);
        Thread.sleep(4000);
        Document document = Jsoup.parse(webDriver.getPageSource());
        Elements elements = document.getElementsByClass("product-list");
        for (Element element : elements) {
            Document doc = Jsoup.parse(element.html());

            Element listElement = doc.getElementsByClass("goodlist").first();

            Document ordersDocument = Jsoup.parse(listElement.html());
            Elements ordersElements = ordersDocument.getElementsByClass("p-wrap exclick");
            System.out.println(ordersElements.size());
            for (Element ordersElement : ordersElements) {
                Product product = new Product();
                product.setUrl(ordersElement.child(0).getElementsByClass("img notranslate").select("a").attr("href"));
                product.setSalePriceRUB(ordersElement.child(1).getElementsByClass("price").first().text());
                product.setRub(ordersElement.child(2).getElementsByClass("price-old").first().text());
                product.setTitle(ordersElement.child(3).getElementsByClass("title").select("a").attr("title"));



                Thread.sleep(1000);
                productList.add(product);
            }

/*
            Elements saleElements = ordersDocument.getElementsByClass("price");


            product.setSalePriceUSD(product.getSalePriceRUB());

            Element priceElement = doc.getElementsByClass("price-old").first();
            product.setRub(priceElement.text());
            product.setUsd(product.getRub());

            Document order = Jsoup.connect(titleElement.attr("href")).get();

            Element ratingElement = order.getElementsByClass("star-num js-star-num").first();
            product.setRating(ratingElement.text());

            Element imgElement = order.getElementsByClass("image-max").first().child(1);
            product.setPicture(imgElement.attr("src"));

            Element reviewsElement = order.getElementsByClass("rating-num J-rating-num").first();
            product.setReviews(reviewsElement.text().replaceAll("[^0-9]", ""));
*/

        }
        productList.forEach(System.out::println);
    }
}
