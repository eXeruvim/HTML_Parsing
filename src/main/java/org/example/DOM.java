package org.example;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DOM {

    public static @NotNull List<Product> getDOM() {

        List<Product> productList = new ArrayList<>();
        String link =
                "https://ru.banggood.com/ru/Wholesale-Computers-and-Office-ca-5001.html?bid=210707&from=nav&a=1670268857.2551&DCC=RU&currency=RUB";
        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;

        try {
            webDriver.get(link);
            //jse.executeScript("scroll(0, 1000);");
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
                product.setTitle(ordersElement.child(3).getElementsByClass("title").select("a").attr("title"));
                product.setReviews(ordersElement.child(4).getElementsByClass("review").select("a").text());

                // Парсинг со страницы товара
                webDriver.get(product.getUrl());
                jse.executeScript("scroll(0, 250);");
                Thread.sleep(2000);
                Document order = Jsoup.parse(webDriver.getPageSource());
                product.setShipping_time(order.getElementsByClass("shipping-time").select("em").text());
                product.setShipping_price(order.getElementsByClass("shipping-price").select("em").text());
                product.setFavorite(order.getElementsByClass("wish-btn exclick").text());


/*                if (webDriver.findElements(By.className("next")).get(0).isDisplayed())
                    webDriver.findElements(By.className("next")).get(0).click();
                product.setPicture(Objects.requireNonNull(order.getElementsByClass("listWrap J_ex_wrap").first()).html());
                */
                product.setPicture(order.getElementsByClass("image-max").select("img").attr("src"));
                product.setRating(order.getElementsByClass("star-num js-star-num").text());
                product.setOption(Objects.requireNonNull(order.getElementsByClass("block-title").select("span").first()).text());
                product.setVariety(order.getElementsByClass("block-cnt").select("a").eachAttr("title"));
                if (order.getElementsByClass("discount").text().isEmpty()){
                    product.setRub(order.getElementsByClass("main-price").select("span").text());
                    product.setUsd(order.getElementsByClass("main-price").attr("oriprice"));
                }
                else {
                    product.setRub(order.getElementsByClass("old-price").select("span").text());
                    product.setUsd(order.getElementsByClass("old-price").attr("oriprice"));
                    product.setSalePriceUSD(order.getElementsByClass("main-price").attr("oriprice"));
                    product.setSalePriceRUB(order.getElementsByClass("main-price").select("span").text());
                }

                productList.add(product);

                webDriver.navigate().back();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            webDriver.manage().deleteAllCookies();
            webDriver.quit();
        }
        return productList;
    }
}