package com.bestselect.search;

import com.bestselect.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URISyntaxException;
import java.net.*;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/search/{keyword}")
    // size
    // sort
    // page : date or name
    public SearchResult getAllUsers(@PathVariable String keyword,
                                    @RequestParam(value = "size", defaultValue = "10") long size,
                                    @RequestParam(value = "page", defaultValue = "1") long page,
                                    @RequestParam(value = "sort", defaultValue = "datetime") String sort,
                                    @RequestParam(value = "order", defaultValue = "asc") String order){
        SearchResult re = null;
        try {

            // param
            Map<String, String> parameters = new HashMap<>();
            parameters.put("query", keyword);
            parameters.put("size", size + "");
            parameters.put("page", page + "");
            parameters.put("sort", sort);
            parameters.put("order", order);

            re = searchService.findBook(keyword, SearchDataSource.INFO.KAKAO, parameters);
        }catch (Exception e){
            e.printStackTrace();
        }
        return re;
    }




}
