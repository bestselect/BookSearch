package com.bestselect.keyword;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface KeywordRepositoty extends CrudRepository<Keyword, String> {
    List<Keyword> findByUserId(String id);
}
