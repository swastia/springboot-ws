package com.sarya.springws.controller;

import com.sarya.springws.model.User;
import com.sarya.springws.model.UserDetailsRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/users",
        produces = {
                MediaType.APPLICATION_XML_VALUE,
                MediaType.APPLICATION_JSON_VALUE
        },
        consumes = {
                MediaType.APPLICATION_XML_VALUE,
                MediaType.APPLICATION_JSON_VALUE
        })
public class UserController {

    public static Map<String,User> users;

    @GetMapping
    public String getUsers(@RequestParam (value="page", defaultValue = "1") int page,
                           @RequestParam(value = "limit", defaultValue = "20") int limit){
        return MessageFormat.format("Get Request called with page: {0} and limit: {1}", page, limit);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<User> getUsers(@PathVariable int userId) {
       if(users.containsKey(userId)){
           return new ResponseEntity<User>(users.get(userId), HttpStatus.OK);
       } else{
           return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
       }
}

    @PostMapping
    public ResponseEntity<User> createUsers(@RequestBody UserDetailsRequest user){
        User userDetailsResponse = new User();

        userDetailsResponse.setlName(user.getlName());
        userDetailsResponse.setfName(user.getfName());
        userDetailsResponse.setEmail(user.getEmail());
        String userId = UUID.randomUUID().toString();
        userDetailsResponse.setId(userId);

        if(users == null){
            users = new HashMap<>();
        }
        users.put(userId, userDetailsResponse  );

        return new ResponseEntity<User>(userDetailsResponse,HttpStatus.OK);
    }

    @PutMapping
    public String updateUsers(String userId, @RequestBody UserDetailsRequest user){

        if(users.isEmpty()){

        }
        return "Update request called";
    }

    @DeleteMapping
    public String deleteUser(){
        return "Delete request called";
    }
}
