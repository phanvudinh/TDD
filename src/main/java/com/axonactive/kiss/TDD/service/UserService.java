package com.axonactive.kiss.TDD.service;

import com.axonactive.kiss.TDD.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUser();
    public User getUserById(long id);
    public User saveUser(User user);
    public void removeUser(User user);
    public String getUserRank(long id);
}
