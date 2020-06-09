package com.ducmp.bai2.model;

public class Book {

    private int idBook;
    private String nameBook;
    private String author;
    private String bookType;
    private int amount;

    public Book() {
    }

    public Book(int idBook, String nameBook, String author, String bookType, int amount) {
        this.idBook = idBook;
        this.nameBook = nameBook;
        this.author = author;
        this.bookType = bookType;
        this.amount = amount;
    }

    public Book(String nameBook, String author, String bookType, int amount) {
        this.nameBook = nameBook;
        this.author = author;
        this.bookType = bookType;
        this.amount = amount;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "idBook=" + idBook +
                ", nameBook='" + nameBook + '\'' +
                ", author='" + author + '\'' +
                ", bookType=" + bookType +
                ", amount=" + amount +
                '}';
    }
}
