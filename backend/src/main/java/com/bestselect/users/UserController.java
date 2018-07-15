package com.bestselect.users;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping("/users/{id}")
    public Users getUser(@PathVariable String id){
        return userService.getUser(id);
    }

    // login
    @RequestMapping(method = RequestMethod.PUT, value = "/users/{token}")
    public Object updatedUser(@PathVariable String token){

        Map<String, String> parameters = new HashMap<>();
        parameters.put("token", token);

        return userService.loginUser(parameters);

    }

    // logout
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public void deleteUser(@PathVariable String id){
        //userService.deleteUser(id);
    }
}
