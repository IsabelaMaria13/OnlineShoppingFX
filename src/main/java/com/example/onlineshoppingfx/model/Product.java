package com.example.onlineshoppingfx.model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

    public Product() {
        this.id = 0;
        this.name = "N/A";
        this.price = 0.0;
        this.stock = 0;
    }

    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public double getPrice() {
        return price;
    }


    public int getStock() {
        return stock;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
