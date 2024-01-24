package com.example.bookstore.entity;

public class OrderDetail {

    private Book book;

    private int quantity;

    private Double price;


    public OrderDetail(Book book, int quantity, Double price ) {
        this.book = book;
        this.quantity = quantity;
        this.price = price;

    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}
