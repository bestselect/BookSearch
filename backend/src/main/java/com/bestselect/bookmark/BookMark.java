package com.bestselect.bookmark;

// 북마크 아이디
// 북마크 추가
// 유저의 북마크 리스트 가져오기
// 유저의 북마크 삭제하기

import com.bestselect.book.Book;
import com.bestselect.users.Users;

import javax.persistence.*;

@Entity
public class BookMark extends Book{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Users user;

    public BookMark() {
    }

    public BookMark(Users user) {
        this.user = user;
    }

    public BookMark(String isbn, String title, String price, String url, String authors, String barcode, String category, String contents, String datetime, String ebook_barcode, String publisher, String sale_price, String sale_yn, String status, String thumbnail, String translators, Users user) {
        super(isbn, title, price, url, authors, barcode, category, contents, datetime, ebook_barcode, publisher, sale_price, sale_yn, status, thumbnail, translators);
        this.user = user;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
