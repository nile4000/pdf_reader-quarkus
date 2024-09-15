package dev.lueem.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class Article {

    @JsonPropertyDescription("Der Name des Artikels")
    private String name;

    @JsonPropertyDescription("Der Preis des Artikels")
    private double price;

    @JsonPropertyDescription("Die Menge des Artikels")
    private int quantity;

    @JsonPropertyDescription("Der angewendete Rabatt auf den Artikel")
    private double discount;

    @JsonPropertyDescription("Der Gesamtpreis nach Rabatt")
    private double total;

    public Article() {
        // Standardkonstruktor
    }

    public Article(String name, double price, int quantity, double discount) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.total = calculateTotal();
    }

    private double calculateTotal() {
        return (price * quantity) * (1 - discount);
    }

    // Getter und Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.total = calculateTotal();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        this.total = calculateTotal();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.total = calculateTotal();
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
        this.total = calculateTotal();
    }

    public double getTotal() {
        return total;
    }
}
