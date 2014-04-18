package com.romanenko.dao.implementation.extractors;


import com.romanenko.entity.Role;
import com.romanenko.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserExtractor implements ResultSetExtractor<User> {

    @Override
    public User extractData(ResultSet rs) throws SQLException, DataAccessException {
        User user = new User(rs.getString("firstName"),rs.getString("lastName"), rs.getString("username"),
                rs.getString("password"),null);
        user.setId(rs.getString("id"));
        Role role = new Role(rs.getString("roleId"), rs.getString("description"), rs.getInt("role"));
        user.setRole(role);
        return user;
    }
}
