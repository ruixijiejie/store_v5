package com.google.store.web.servlet;

import com.google.store.domain.User;
import com.google.store.service.UserService;
import com.google.store.service.serviceImp.UserServiceImp;
import com.google.store.utils.MailUtils;
import com.google.store.utils.MyBeanUtils;
import com.google.store.utils.UUIDUtils;
import com.google.store.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class UserServlet extends BaseServlet {

    //registUI
    public String registUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return "/jsp/register.jsp";
    }

    // loginUI
    public String loginUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return "/jsp/login.jsp";
    }

    //userRegist
    public String userRegist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //1_接受用户数据,
        Map<String, String[]> map = req.getParameterMap();
        User user = new User();
        MyBeanUtils.populate(user, map);
        // 为用户的其他属性赋值
        user.setUid(UUIDUtils.getId());
        user.setState(0);
        user.setCode(UUIDUtils.getCode());
        System.out.println(user);
        //3_调用业务层注册功能,向用户邮箱发送一份激活邮件
        UserService UserService = new UserServiceImp();
        try {
            UserService.userRegist(user);
            MailUtils.sendMail(user.getEmail(), user.getCode());
            req.setAttribute("msg", "用户注册成功,请激活!");
        } catch (Exception e) {
            req.setAttribute("msg", "用户注册失败,请重新注册!");
        }
        //4_向客户端提示:用户注册成功,请激活,转发到提示页面
        return "/jsp/info.jsp";
    }

    //active
    public String active(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //服务端获取到激活码,和数据库中已经存在的激活码对比,如果存在,激活成功,更改用户激活状态1,转发到登录页面,提示:激活成功,请登录.
        String code = req.getParameter("code");
        //调用业务层功能:根据激活码查询用户 select * from user where code=?
        UserService UserService = new UserServiceImp();
        boolean flag = UserService.userActive(code);
        //如果用户不为空,激活码正确的,可以激活
        if (flag == true) {
            req.setAttribute("msg", "用户激活成功,请登录");
            return "/jsp/login.jsp";
        } else {
            req.setAttribute("msg", "用户激活失败,请重新激活");
            return "/jsp/info.jsp";
        }
    }

    // userLogin
    public String userLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取用户数据
        User user = new User();
        MyBeanUtils.populate(user, req.getParameterMap());

        // 调用业务层的登陆功能
        UserService UserService = new UserServiceImp();
        User user02 = null;
        try {
            user02 = UserService.userLogin(user);
            // 将用户信息放入session中
            req.getSession().setAttribute("loginUser", user02);
            resp.sendRedirect("/store_v5/index.jsp");
            return null;
            // 用户登陆成功
        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println(msg);
            req.setAttribute("msg", msg);
            return "/jsp/login.jsp";
        }
    }

    // logOut
    public String logOut(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.getSession().invalidate();
        resp.sendRedirect("/store_v5/index.jsp");
        return null;
    }
}
