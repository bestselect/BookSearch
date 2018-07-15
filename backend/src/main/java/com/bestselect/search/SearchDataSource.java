package com.bestselect.search;

import com.bestselect.book.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SearchDataSource {

    enum INFO {
        KAKAO("1", "kakao"),
        EMPTY("-1", "empty");

        private String id;
        private String name;

        INFO(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public SearchResult findBook(String keyword, SearchDataSource.INFO info, Map<String, String> parameters);
}
