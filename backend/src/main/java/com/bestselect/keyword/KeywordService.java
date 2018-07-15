package com.bestselect.keyword;

import com.bestselect.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class KeywordService {

    @Autowired
    private KeywordRepositoty keywordRepositoty;

    public List<Keyword> getAllKeywords(String userId) {
        return keywordRepositoty.findByUserId(userId);
    }

    public void addKeyword(Keyword keyword){ keywordRepositoty.save(keyword); }

    public void deleteKeyword (Keyword keyword){
        keywordRepositoty.delete(keyword);
    }

/**
    public Keyword getUser(String id){
        return keywordRepositoty.findOne(id);
    }


    public void updateUser(String id, Keyword user){
        keywordRepositoty.save(user);
    }

 **/

}
