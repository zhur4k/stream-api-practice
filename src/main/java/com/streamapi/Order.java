package com.streamapi;

public class Order {
    private String product;
    private double cost;

    public Order(String product, double cost) {
        this.product = product;
        this.cost = cost;
    }

    public String getProduct() {
        return product;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Order{" +
                "product='" + product + '\'' +
                ", cost=" + cost +
                '}';
    }
}
