package com.example.ndsp.Pojo;

public class RecentBook {
    private String id,bookTitle,bookCoverImgUrl,bookSalePrice, authors,rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookCoverImgUrl() {
        return bookCoverImgUrl;
    }

    public void setBookCoverImgUrl(String bookCoverImgUrl) {
        this.bookCoverImgUrl = bookCoverImgUrl;
    }

    public String getBookSalePrice() {
        return bookSalePrice;
    }

    public void setBookSalePrice(String bookSalePrice) {
        this.bookSalePrice = bookSalePrice;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
