package com.google.store.dao;

import com.google.store.domain.Order;
import com.google.store.domain.OrderItem;
import com.google.store.domain.User;

import java.sql.Connection;
import java.util.List;

public interface OrderDao {

    void saveOrder(Connection coon, Order order) throws Exception;

    void saveOrderItem(Connection coon, OrderItem item) throws Exception;

    int getTotalRecords(User user) throws Exception;

    List findMyOrdersWithPage(User user, int startIndex, int pageSize) throws Exception;

    Order findOrderByOid(String oid) throws Exception;
}
