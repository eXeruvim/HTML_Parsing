package org.example;

import java.util.List;

public class Product {
    private String title;
    private String url;
    private String usd;
    private String rub;
    private String picture;
    private String salePriceRUB;
    private String salePriceUSD;
    private String rating;
    private String reviews;
    private String option;
    private List<String> variety;
    private String shipping_time;
    private String favorite;
    private String shipping_price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsd() {
        return usd;
    }

    public void setUsd(String usd) {
        this.usd = usd;
    }

    public String getRub() {
        return rub;
    }

    public void setRub(String rub) {
        this.rub = rub;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setSalePriceRUB(String salePriceRUB) {
        this.salePriceRUB = salePriceRUB;
    }

    public String getSalePriceRUB() {
        return salePriceRUB;
    }

    public String getSalePriceUSD() {
        return salePriceUSD;
    }

    public void setSalePriceUSD(String salePriceUSD) {
        this.salePriceUSD = salePriceUSD;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews.replaceAll("[^0-9,]", "");
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public List<String> getVariety() {
        return variety;
    }

    public void setVariety(List<String> variety) {
        this.variety = variety;
    }

    public String getShipping_time() {
        return shipping_time;
    }

    public void setShipping_time(String shipping_time) {
        this.shipping_time = shipping_time;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getShipping_price() {
        return shipping_price;
    }

    public void setShipping_price(String shipping_price) {
        this.shipping_price = shipping_price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", picture='" + picture + '\'' +
                ", rating='" + rating + '\'' +
                ", reviews='" + reviews + '\'' +
                option + " " + variety.toString() + "\n" +
                "Ссылка = '" + url + '\'' + "\n" +
                "Цена = '" + rub + " (" + usd + "$)" + '\'' + "\n" +
                "Цена по акции = '" + salePriceRUB + " (" + salePriceUSD + "$)"  + '\'' + "\n" +
                ", shipping_time='" + shipping_time + '\'' +
                ", favorite='" + favorite + '\'' +
                ", shipping_price='" + shipping_price + '\'' +
                '}';
    }
}
