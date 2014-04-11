package com.romanenko.dao.implementation;

import com.romanenko.dao.UserStorage;
import com.romanenko.dao.exceptions.DaoException;
import com.romanenko.dao.exceptions.NoSuchEntityException;
import com.romanenko.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserStorageImpl implements UserStorage {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertUser;


    /*public void UserStorageImpl(DataSource dataSource,
                                NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.mainDataSource = dataSource;
        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    } */
    @Autowired
    public void setMainDataSource(DataSource mainDataSource) {
        this.insertUser = new SimpleJdbcInsert(mainDataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(mainDataSource);

    }

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void setInsertUser(SimpleJdbcInsert insertUser) {
        this.insertUser = insertUser;
    }

    @Transactional
    @Override
    public User findByUserName(String username) throws DaoException {
        User user;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);
        try {
                user = this.namedParameterJdbcTemplate.queryForObject(
                "SELECT * FROM users WHERE username= :username",
                params,
                ParameterizedBeanPropertyRowMapper.newInstance(User.class)
                );

        } catch (EmptyResultDataAccessException ex) {
             throw new NoSuchEntityException("Can't obtain user with name:" + username,ex);
        }
        return user;
    }

    @Transactional
    @Override
    public User findByUserId(String id) throws DaoException {
        User user;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        try {
            user = this.namedParameterJdbcTemplate.queryForObject(
                    "SELECT * FROM users WHERE id= :id",
                    params,
                    ParameterizedBeanPropertyRowMapper.newInstance(User.class)
            );

        } catch (EmptyResultDataAccessException ex) {
            throw new NoSuchEntityException("Can't obtain user with such id " + id, ex);
        }
        return user;
    }
    @Transactional
    @Override
    public List<User> findAll() throws NoSuchEntityException {
        List<User> allUsers;
        try {
            allUsers =(List<User>) this.namedParameterJdbcTemplate.query("SELECT * FROM users;",
                    (SqlParameterSource) null,
                    ParameterizedBeanPropertyRowMapper.newInstance(User.class)
            );

        } catch (EmptyResultDataAccessException ex) {
            throw new NoSuchEntityException("Can't obtain any user ", ex);
        }
        return allUsers;
    }
    @Transactional
    @Override
    public void save(User user) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
        if (user.isNew()) {
            Number newKey = this.insertUser.executeAndReturnKey(parameterSource);
            user.setId(newKey.toString());
        } else {
            this.namedParameterJdbcTemplate.update(
                    "UPDATE owners SET first_name=:firstName, last_name=:lastName, username=:username, " +
                            "password=:password WHERE id=:id",
                    parameterSource);
        }
    }


    @Override
    public Boolean delete(User user) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}