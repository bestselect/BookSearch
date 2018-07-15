package com.bestselect.bookmark;

import com.bestselect.users.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookMarkRepository extends CrudRepository<BookMark, String> {
    List<BookMark> findBookMarkByUser(Users users);
}
