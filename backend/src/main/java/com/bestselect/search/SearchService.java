package com.bestselect.search;

import com.bestselect.book.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SearchService {

    public SearchResult findBook(String keyword, SearchDataSource.INFO info, Map<String, String> parameters ) {

        SearchDataSource dataSource;
        if(info.equals( SearchDataSource.INFO.KAKAO)){
            dataSource = new SearchDataSourceKaKao();
        }else{
            return null;
        }

        SearchResult re = dataSource.findBook(keyword, info, parameters);

        return re;
    }
}