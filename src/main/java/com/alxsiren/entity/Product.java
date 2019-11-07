package com.alxsiren.entity;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
    String name;
    String brand;
    String color;
    String price;
    String articleId;


    public Product() {
    }

    public Product(String name, String brand, String color, String price, String articleId) {
        this.name = name;
        this.brand = brand;
        this.color = color;
        this.price = price;
        this.articleId = articleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                Objects.equals(brand, product.brand) &&
                Objects.equals(color, product.color) &&
                Objects.equals(price, product.price) &&
                Objects.equals(articleId, product.articleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, brand, color, price, articleId);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", price='" + price + '\'' +
                ", articleId='" + articleId + '\'' +
                '}';
    }
}
