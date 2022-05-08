package com.techelevator;

import java.math.BigDecimal;

public class Product {

    private String name;
    private String description;
    private BigDecimal price;
    private int inventory;

    public Product(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.inventory = 7;
    }

    public void subtractInventory() {
        this.inventory = this.inventory - 1;
    }



    @Override
    public String toString() {
        return "Product: " + name + '\'' +
                ", description: " + description + '\'' +
                ", price: " + price +
                ", inventory: " + inventory +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public int getInventory() {
        return inventory;
    }
}
