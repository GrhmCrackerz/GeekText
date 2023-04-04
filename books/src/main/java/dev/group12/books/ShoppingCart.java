package dev.group12.books;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Book> items;
    private double subtotal;
    private double totalCost;
    private String username;

    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.subtotal = 0.0;
        this.totalCost = 0.0;
        this.username = "";
    }
    public void addItem(Book book) {
        items.add(book);
        subtotal += book.getPrice();
        totalCost = subtotal * 1;
    }
    public void reset() {
        items.clear();
        subtotal = 0.0;
        totalCost = 0.0;
        username = "";
    }
    public void removeItem(Book book) {
        items.remove(book);
        subtotal -= book.getPrice();
        totalCost = subtotal * 1;

        if (subtotal < 0) {
            reset();
        }
    }

    public List<Book> getItems() {
        return items;
    }

    public void setItems(List<Book> items) {
        this.items = items;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Add and remove item methods

}