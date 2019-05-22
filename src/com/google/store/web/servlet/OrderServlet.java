package com.google.store.web.servlet;

import com.google.store.domain.*;
import com.google.store.service.OrderService;
import com.google.store.service.serviceImp.OrderServiceImp;
import com.google.store.utils.PageModel;
import com.google.store.utils.UUIDUtils;
import com.google.store.web.base.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class OrderServlet extends BaseServlet {

    // saveOrder
    public String saveOrder(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 确认用户是登陆状态
        User user = (User) req.getSession().getAttribute("loginUser");
        if (null == user) {
            req.setAttribute("msg", "请登录后再下单");
            return "/jsp/info.jsp";
        }
        // 获取购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 创建订单对象，为订单对象赋值
        Order order = new Order();
        order.setOid(UUIDUtils.getCode());
        order.setOrderTime(new Date());
        order.setTotal(cart.getTotal());
        order.setState(1);
        order.setUser(user);
        // 创建订单项
        for (CartItem item : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setItemid(UUIDUtils.getCode());
            orderItem.setQuantity(item.getNum());
            orderItem.setTotal(item.getSubTotal());
            orderItem.setProduct(item.getProduct());
            // 设置当前的订单项属于哪个订单
            orderItem.setOrder(order);
            order.getList().add(orderItem);
        }
        // 保存订单
        OrderService orderService = new OrderServiceImp();
        orderService.saveOrder(order);
        // 清空购物车
        cart.clearCart();
        // 将订单存放到request中
        req.setAttribute("order", order);
        // 转发到
        return "/jsp/order_info.jsp";
    }

    // findMyOrdersWithPage
    public String findMyOrdersWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取用户信息
        User user = (User) req.getSession().getAttribute("loginUser");
        // 获取当前页
        int curNum = Integer.parseInt(req.getParameter("num"));
        // 调用业务层逻辑
        OrderService orderService = new OrderServiceImp();
        PageModel pm = orderService.findMyOrdersWithPage(user, curNum);
        req.setAttribute("page", pm);
        return "/jsp/order_list.jsp";
    }

    // findOrderByOid
    public String findOrderByOid(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取到订单oid
        String oid = req.getParameter("oid");
        //调用业务层功能:根据订单编号查询订单信息
        OrderService OrderService = new OrderServiceImp();
        Order order = OrderService.findOrderByOid(oid);
        // 将订单放入request
        req.setAttribute("order", order);
        // 转发到/jsp/order_info.jsp
        return "/jsp/order_info.jsp";
    }
}
