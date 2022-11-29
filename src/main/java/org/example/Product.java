package org.example;

public class Product {
    private String title;
    private String url;
    private String usd;
    private String rub;
    private String picture;

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

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", usd='" + usd + '\'' +
                ", rub='" + rub + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
