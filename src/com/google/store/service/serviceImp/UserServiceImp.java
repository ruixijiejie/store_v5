package com.google.store.service.serviceImp;

import com.google.store.dao.UserDao;
import com.google.store.dao.daoImp.UserDaoImp;
import com.google.store.domain.User;
import com.google.store.service.UserService;

import java.sql.SQLException;

public class UserServiceImp implements UserService {

    @Override
    public void userRegist(User user) throws SQLException {
        UserDao Userdao = new UserDaoImp();
        Userdao.userRegist(user);
    }

    @Override
    public boolean userActive(String code) throws SQLException {
        UserDao Userdao = new UserDaoImp();

        User user = Userdao.userActive(code);
        // 可以根据激活码查询到一个用户
        if (null != user) {
            user.setState(1);
            user.setCode(null);
            // 对数据库执行真实的更新操作
            Userdao.updateUser(user);
            return true;
        } else {
            return  false;
        }
    }

    @Override
    public User userLogin(User user) throws SQLException {
        UserDao UserDao = new UserDaoImp();
        User uu = UserDao.userLogin(user);
        if (null == uu) {
            throw new RuntimeException("密码有误！");
        } else if (uu.getState() == 0) {
            throw new RuntimeException("用户未激活！");
        } else {
            return uu;
        }
    }
}
