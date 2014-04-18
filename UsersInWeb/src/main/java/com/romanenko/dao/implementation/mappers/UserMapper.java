package com.romanenko.dao.implementation.mappers;

import com.romanenko.dao.implementation.extractors.UserExtractor;
import com.romanenko.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
         UserExtractor extractor = new UserExtractor();
         return extractor.extractData(rs);
    }
}



