package com.romanenko.dao;

import com.romanenko.dao.exceptions.DaoException;
import com.romanenko.dao.exceptions.NoSuchEntityException;
import com.romanenko.entity.User;

import java.util.List;

public interface UserStorage {
    public User findByUserName(String username) throws DaoException;

    public User findByUserId(String id) throws DaoException;

    public List<User> findAll() throws NoSuchEntityException;

    public void save(User user);

    public Boolean delete(User user);
}
