package com.bestselect.bookmark;

import com.bestselect.book.Book;
import com.bestselect.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookMarkService {

    @Autowired
    private BookMarkRepository bookMarkRepositoty;

    public List<BookMark> getBookMark(String id){
        return bookMarkRepositoty.findBookMarkByUser(new Users(id,"",""));
    }

    public List<BookMark> addBookMark(Book book, String user_id){

        BookMark bookMark = new BookMark();
        bookMark.setIsbn(book.getIsbn());
        bookMark.setTitle(book.getTitle());
        bookMark.setPrice(book.getPrice());
        bookMark.setUrl(book.getUrl());
        bookMark.setAuthors(book.getAuthors());
        bookMark.setBarcode(book.getBarcode());
        bookMark.setCategory(book.getCategory());
        bookMark.setContents(book.getContents());
        bookMark.setDatetime(book.getDatetime());
        bookMark.setEbook_barcode(book.getEbook_barcode());
        bookMark.setPublisher(book.getPublisher());
        bookMark.setSale_price(book.getSale_price());
        bookMark.setSale_yn(book.getSale_yn());
        bookMark.setStatus(book.getStatus());
        bookMark.setThumbnail(book.getThumbnail());
        bookMark.setTranslators(book.getTranslators());

        bookMark.setUser(new Users(user_id, "", ""));

        bookMarkRepositoty.save(bookMark);

        return bookMarkRepositoty.findBookMarkByUser(new Users(user_id,"",""));
    }

    public List<BookMark> deleteBookMark(String user_id, long id){

        BookMark bookMark = new BookMark();
        bookMark.setId(id);
        bookMarkRepositoty.delete(bookMark);

        return bookMarkRepositoty.findBookMarkByUser(new Users(user_id,"",""));

    }

}
