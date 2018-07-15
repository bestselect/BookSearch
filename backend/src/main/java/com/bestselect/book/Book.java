package com.bestselect.book;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String isbn;
    private String title;
    private String price;
    private String url;
    private String authors;
    private String barcode;
    private String category;
    private String contents;
    private String datetime;
    private String ebook_barcode;
    private String publisher;
    private String sale_price;
    private String sale_yn;
    private String status;
    private String thumbnail;
    private String translators;

    public Book() {
    }

    public Book(String isbn, String title, String price, String url, String authors, String barcode, String category, String contents, String datetime, String ebook_barcode, String publisher, String sale_price, String sale_yn, String status, String thumbnail, String translators) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.url = url;
        this.authors = authors;
        this.barcode = barcode;
        this.category = category;
        this.contents = contents;
        this.datetime = datetime;
        this.ebook_barcode = ebook_barcode;
        this.publisher = publisher;
        this.sale_price = sale_price;
        this.sale_yn = sale_yn;
        this.status = status;
        this.thumbnail = thumbnail;
        this.translators = translators;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getEbook_barcode() {
        return ebook_barcode;
    }

    public void setEbook_barcode(String ebook_barcode) {
        this.ebook_barcode = ebook_barcode;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getSale_yn() {
        return sale_yn;
    }

    public void setSale_yn(String sale_yn) {
        this.sale_yn = sale_yn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTranslators() {
        return translators;
    }

    public void setTranslators(String translators) {
        this.translators = translators;
    }
}
