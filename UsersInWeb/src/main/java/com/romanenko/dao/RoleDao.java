package com.romanenko.dao;


import com.romanenko.dao.exceptions.DaoException;
import com.romanenko.entity.Role;
import com.romanenko.entity.User;

public interface RoleDao {
    public Role findByRoleId(String roleid) throws DaoException;

    public Role getByUser(User user) throws DaoException;

}
