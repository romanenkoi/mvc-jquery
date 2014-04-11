package com.romanenko.service;

import com.romanenko.dao.UserStorage;
import com.romanenko.dao.exceptions.DaoException;
import com.romanenko.dao.exceptions.NoSuchEntityException;
import com.romanenko.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {

    @Autowired
    private UserStorage userStorage;

    public List<User> findAll() {
        try {
            return  userStorage.findAll();
        } catch (NoSuchEntityException e) {
            /*NOP*/
            return null;
        }
    }

    public void setStorage(UserStorage storage){
        this.userStorage = storage;
    }

    public User create(User user) {
        userStorage.save(user);
        try {
            return userStorage.findByUserName(user.getId());
        } catch (DaoException e) {
            return null;
        }
    }

    public User update(User user){
        userStorage.save(user);

        try {
            return userStorage.findByUserId(user.getId());
        } catch (DaoException e) {
           return null;
        }
    }

    public boolean delete(User user){
        return false;
    }

    public User read(User user){
        try {
            return userStorage.findByUserId(user.getId());
        } catch (DaoException e) {
            return null;
        }
    }
}
