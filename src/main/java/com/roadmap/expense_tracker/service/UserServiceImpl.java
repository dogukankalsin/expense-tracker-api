package com.roadmap.expense_tracker.service;

import com.roadmap.expense_tracker.entity.User;
import com.roadmap.expense_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements  UserService{
    UserRepository userRp;
    @Autowired
    public UserServiceImpl(UserRepository userRp){
        this.userRp=userRp;
    }
    @Override
    public User save(User user) {
        return userRp.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRp.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRp.findById(id).orElseThrow();
    }

    @Override
    public User findByEmail(String email) {
        return userRp.findByEmail(email).orElseThrow();
    }

    @Override
    public User update(Long id, User user) {
        User oldUser= userRp.findById(id).orElseThrow();
        oldUser.setUsername(user.getUsername());
        oldUser.setEmail(user.getEmail());
        oldUser.setExpenses(user.getExpenses());
        oldUser.setPassword(user.getPassword());
        return userRp.save(oldUser);
    }

    @Override
    public void delete(Long id) {
        User user=userRp.findById(id).orElseThrow();
        userRp.delete(user);
    }
}
