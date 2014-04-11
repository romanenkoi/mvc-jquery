package com.romanenko.dao.implementation;


import com.romanenko.dao.RoleDao;
import com.romanenko.dao.exceptions.DaoException;
import com.romanenko.dao.exceptions.NoSuchEntityException;
import com.romanenko.entity.Role;
import com.romanenko.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class RoleGetter implements RoleDao {


    private NamedParameterJdbcTemplate template;

    @Autowired
    public void setMainDataSource(DataSource mainDataSource) {
        this.template = template;
        this.template = new NamedParameterJdbcTemplate(mainDataSource);
    }


    @Override
    public Role findByRoleId(String roleId) throws DaoException {
        Role role;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("role", roleId);
        try {
            role = this.template.queryForObject(
                    "SELECT * FROM roles WHERE role= :role",
                    params,
                    ParameterizedBeanPropertyRowMapper.newInstance(Role.class)
            );

        } catch (EmptyResultDataAccessException ex) {
            throw new NoSuchEntityException("Can't obtain role with id:" + roleId,ex);
        }
        return role;
    }

    @Override
    public Role getByUser(User user) throws DaoException {
        return findByRoleId(user.getRole().getId());
    }


}
