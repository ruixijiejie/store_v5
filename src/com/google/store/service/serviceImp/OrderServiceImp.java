package com.google.store.service.serviceImp;

import com.google.store.dao.OrderDao;
import com.google.store.dao.daoImp.OrderDaoImp;
import com.google.store.domain.Order;
import com.google.store.domain.OrderItem;
import com.google.store.domain.User;
import com.google.store.service.OrderService;
import com.google.store.utils.JDBCUtils;
import com.google.store.utils.PageModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImp implements OrderService {

    OrderDao orderDao = new OrderDaoImp();

    @Override
    public void saveOrder(Order order) throws SQLException {
        // 保存订单和订单下所有的订单项(同时成功 同时失败)
       /* try {
            JDBCUtils.startTransaction();
            OrderDao orderDao = new OrderDaoImp();
            orderDao.saveOrder(order);
            for (OrderItem item : order.getList()) {
                orderDao.saveOrderItem(item);
            }
            JDBCUtils.commitAndClose();
        } catch (Exception e) {
            JDBCUtils.rollbackAndClose();
        }*/

        Connection coon = null;
        try {
            // 获取连接
            coon = JDBCUtils.getConnection();
            // 开启事务
            coon.setAutoCommit(false);
            // 保存订单
            orderDao.saveOrder(coon, order);
            // 保存订单项
            for (OrderItem item : order.getList()) {
                orderDao.saveOrderItem(coon, item);
            }
            // 提交
            coon.commit();
        } catch (Exception e) {
            // 回滚
            coon.rollback();
        }
    }

    @Override
    public PageModel findMyOrdersWithPage(User user, int curNum) throws Exception {
        // 创建pageModel对象 计算并携带分页参数
        int totalRecords = orderDao.getTotalRecords(user);
        PageModel pm = new PageModel(curNum, totalRecords, 3);
        // 关联集合
        List list = orderDao.findMyOrdersWithPage(user, pm.getStartIndex(), pm.getPageSize());
        pm.setList(list);
        // 关联url
        pm.setUrl("OrderServlet?method=findMyOrdersWithPage");
        return pm;
    }

    @Override
    public Order findOrderByOid(String oid) throws Exception {
        return orderDao.findOrderByOid(oid);
    }
}
