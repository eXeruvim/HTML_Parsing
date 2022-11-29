package org.example;

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
        this.usd = String.valueOf(Math.round(Float.parseFloat(getRub()) / 61.07));
    }

    public String getRub() {
        return rub;
    }

    public void setRub(String rub) {
        this.rub = rub.replaceAll("\\.", "").replaceAll("\\s", "").replaceAll("[^0-9,]", "").replaceAll(",", ".");
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getsalePriceRUB() {
        return salePriceRUB;
    }

    public void setSalePriceRUB(String salePriceRUB) {
        this.salePriceRUB = salePriceRUB.replaceAll("\\.", "").replaceAll("\\s", "").replaceAll("[^0-9,]", "").replaceAll(",", ".");
    }

    public String getSalePriceRUB() {
        return salePriceRUB;
    }

    public String getSalePriceUSD() {
        return salePriceUSD;
    }

    public void setSalePriceUSD(String salePriceUSD) {
        this.salePriceUSD = String.valueOf(Math.round(Float.parseFloat(getSalePriceRUB()) / 61.07));
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
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Товар = '" + title + '\'' + "\n" +
                "Ссылка = '" + url + '\'' + "\n" +
                "Цена = '" + rub + " руб. (" + usd + " $)" + '\'' + "\n" +
                "Изображение = '" + picture + '\'' + "\n" +
                "Цена по акции = '" + salePriceRUB + " руб. (" + salePriceUSD + " $)"  + '\'' + "\n" +
                "Рейтинг = '" + rating + '\'' + "\n" +
                "Отзывов = '" + reviews + '\'' +
                '}';
    }
}
