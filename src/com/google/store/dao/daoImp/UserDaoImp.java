package com.google.store.dao.daoImp;

import com.google.store.dao.UserDao;
import com.google.store.domain.User;
import com.google.store.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImp implements UserDao {
    @Override
    public void userRegist(User user) throws SQLException {
        String sql = "INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode()};
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        qr.update(sql, params);
    }

    @Override
    public User userActive(String code) throws SQLException {
        String sql = "select * from user where code=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        User user = qr.query(sql, new BeanHandler<User>(User.class), code);
        return user;
    }

    @Override
    public void updateUser(User user) throws SQLException {
        String sql = "update user set username= ? ,password=? ,name =? ,email =? ,telephone =? , birthday =?  ,sex =? ,state= ? , code = ? where uid=?";
        Object[] params = {user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode(), user.getUid()};
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        qr.update(sql, params);
    }

    @Override
    public User userLogin(User user) throws SQLException {
        String sql = "select * from user where username=? and password=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanHandler<User>(User.class), user.getUsername(), user.getPassword());
    }
}
