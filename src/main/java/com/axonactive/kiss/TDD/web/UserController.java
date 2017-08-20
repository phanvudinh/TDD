package com.axonactive.kiss.TDD.web;

import com.axonactive.kiss.TDD.exception.NotFoundException;
import com.axonactive.kiss.TDD.model.Response;
import com.axonactive.kiss.TDD.model.User;
import com.axonactive.kiss.TDD.repository.BookRepository;
import com.axonactive.kiss.TDD.service.BookService;
import com.axonactive.kiss.TDD.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value="/user", method= RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUser(){
        logger.info("Returning all the user");

        return new ResponseEntity<List<User>>(userService.getAllUser(), HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) throws NotFoundException {
        logger.info("User id to return " + id);

        User user = userService.getUserById(id);
        if (user == null){
            throw new NotFoundException("User doesn´t exist");
        }
        return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> removeUserById(@PathVariable("id") long id) throws NotFoundException {
        logger.info("User id to remove " + id);

        User user = userService.getUserById(id);
        if (user == null){
            throw new NotFoundException("User to delete doesn´t exist");
        }
        userService.removeUser(user);
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "User has been deleted"), HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> saveUser(@RequestBody User user) throws NotFoundException {
        logger.info("Payload to save " + user);

        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PATCH)
    public ResponseEntity<User> updateUser(@RequestBody User userInfo) throws NotFoundException {
        logger.info("Payload to update " + userInfo);

        User user = userService.getUserById(userInfo.getId());
        if (user == null){
            throw new NotFoundException("User to update doesn´t exist");
        }
        return new ResponseEntity<User>(userService.saveUser(userInfo), HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}/rank", method = RequestMethod.GET)
    public ResponseEntity<String> updateUser(@PathVariable long id) throws NotFoundException {
        return new ResponseEntity<String>(userService.getUserRank(id), HttpStatus.OK);
    }

}
