package com.bestselect.bookmark;

import com.bestselect.book.Book;
import com.bestselect.error.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1")
public class BookMarkController {

    @Autowired
    private BookMarkService bookmarkService;

    @RequestMapping("/users/{id}/bookmark")
    public List<BookMark> getBookMark(@PathVariable String id){
        return bookmarkService.getBookMark(id);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/users/{id}/bookmark")
    public List<BookMark> addBookMark(@RequestBody Book book, @PathVariable String id){
        return bookmarkService.addBookMark(book, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{user_id}/bookmark/{id}")
    public List<BookMark> deleteUser(@PathVariable String user_id, @PathVariable long id){
        return bookmarkService.deleteBookMark(user_id, id);
    }
}
