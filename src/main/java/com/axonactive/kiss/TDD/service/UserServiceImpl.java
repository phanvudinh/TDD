package com.axonactive.kiss.TDD.service;

import com.axonactive.kiss.TDD.model.User;
import com.axonactive.kiss.TDD.repository.BookRepository;
import com.axonactive.kiss.TDD.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Qualifier("bookRepository")
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void removeUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public String getUserRank(long id) {
        String rank="";
        int numberOfBooks = bookRepository.countByUserId(id);

        if(numberOfBooks == 0){
             rank = "NON";
        }else if(numberOfBooks < 10){
            rank = "D";
        }else if(numberOfBooks < 20){
            rank = "C";
        }else if(numberOfBooks < 30){
            rank = "B";
        }else{
            rank = "A";
        }

        return rank;
    }
}
