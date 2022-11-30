package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Currency {
    public static String getUsdCurrency() throws IOException {
        Document document = Jsoup.connect("https://cbr.ru/key-indicators/").get();
        return document.getElementsByClass("value td-w-4 _bold _end mono-num").first().text().replace(",", ".");
    }
}
