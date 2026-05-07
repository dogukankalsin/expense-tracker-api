package com.roadmap.expense_tracker.service;

import com.roadmap.expense_tracker.entity.User;
import com.roadmap.expense_tracker.exception.EmailAlreadyExistsException;
import com.roadmap.expense_tracker.exception.UserNotFoundException;
import com.roadmap.expense_tracker.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements  UserService{
    UserRepository userRp;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRp,PasswordEncoder passwordEncode){
        this.userRp=userRp;
        this.passwordEncoder=passwordEncode;
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
        return userRp.findById(id).orElseThrow(()->new UserNotFoundException("User not found with id"+ id));
    }

    @Override
    public User findByEmail(String email) {
        return userRp.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not found with email"+ email));
    }

    @Override
    public User update(Long id, User user) {
        User oldUser= userRp.findById(id).orElseThrow(()->new UserNotFoundException("User not found with id"+ id));
        oldUser.setUsername(user.getUsername());
        if(!oldUser.getEmail().equals(user.getEmail()) && userRp.existsByEmail(user.getEmail())){
            throw new EmailAlreadyExistsException("Email zaten kullanılıyor");
        }
        oldUser.setEmail(user.getEmail());
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRp.save(oldUser);
    }

    @Override
    public void delete(Long id) {
        User user=userRp.findById(id).orElseThrow(()->new UserNotFoundException("User not found with id"+ id));
        userRp.delete(user);
    }
}
