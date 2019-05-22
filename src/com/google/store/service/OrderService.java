package com.google.store.service;

import com.google.store.domain.Order;
import com.google.store.domain.User;
import com.google.store.utils.PageModel;

public interface OrderService {

    void saveOrder(Order order) throws Exception;

    PageModel findMyOrdersWithPage(User user, int curNum) throws Exception;

    Order findOrderByOid(String oid) throws Exception;
}
