package com.bestselect.keyword;

import com.bestselect.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class KeywordController {

    @Autowired
    private KeywordService keywordService;


    @RequestMapping("/users/{id}/keywords")
    public List<Keyword> getAllUsers(@PathVariable String id){
        return keywordService.getAllKeywords(id);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/users/{id}/keyword")
    public List<Keyword> addUser(@RequestBody Keyword keyword, @PathVariable String id){

        keyword.setUser(new Users(id, "", ""));
        keywordService.addKeyword(keyword);

        return keywordService.getAllKeywords(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}/keywords")
    public List<Keyword> deleteUser(@RequestBody Keyword keyword, @PathVariable String id){
        keywordService.deleteKeyword(keyword);
        return keywordService.getAllKeywords(id);
    }
}
