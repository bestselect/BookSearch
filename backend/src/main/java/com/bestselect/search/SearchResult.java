package com.bestselect.search;

import com.bestselect.book.Book;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {

    List<Book> books;
    SearchMeta meta;

    public SearchResult() {
    }

    public SearchResult(List<Book> books, SearchMeta meta) {
        this.books = books;
        this.meta = meta;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public SearchMeta getMeta() {
        return meta;
    }

    public void setMeta(SearchMeta meta) {
        this.meta = meta;
    }

}
