package com.romanenko.service;

import com.romanenko.dao.UserStorage;
import com.romanenko.dao.exceptions.DaoException;
import com.romanenko.dao.exceptions.NoSuchEntityException;
import com.romanenko.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserService {

    //dao of users injected in service layer
    @Autowired
    private UserStorage userStorage;

    public void setStorage(UserStorage storage){
        this.userStorage = storage;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        try {
            return  userStorage.findAll();
        } catch (NoSuchEntityException e) {
            /*NOP*/
            return null;
        }
    }

    @Transactional(readOnly = false , rollbackFor = Exception.class)
    public User create(User user) {
        userStorage.save(user);
        try {
            return userStorage.findByUserName(user.getId());
        } catch (DaoException e) {
            return null;
        }
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public User update(User user){
        userStorage.save(user);
        try {
            return userStorage.findByUserId(user.getId());
        } catch (DaoException e) {
           return null;
        }
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public boolean delete(User user){
        return false;
    }

    @Transactional(readOnly = true)
    public User read(User user){
        try {
            return userStorage.findByUserId(user.getId());
        } catch (DaoException e) {
            return null;
        }
    }
}
