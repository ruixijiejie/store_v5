package com.google.store.domain;

import java.util.ArrayList;
import java.util.List;

public class Cart02 {
    //购物车上的个数不确定的购物项
    private List<CartItem> list = new ArrayList<CartItem>();
    //总计
    private double total;

    //1_向购物车中添加购物项
    public void addCartItemToCart(CartItem cartItem) {

        if (list.size() > 0) {
            for (CartItem it : list) {
                if (it.getProduct().getPid().equals(cartItem.getProduct().getPid())) {
                    it.setNum(it.getNum() + cartItem.getNum());
                    return;
                }
            }
            list.add(cartItem);
        } else {
            list.add(cartItem);
        }
    }

    //2_从购物车中移除单个购物项
    public void removeCartItem(String pid) {
        if (list.size() > 0) {
            for (CartItem item : list) {
                if (item.getProduct().getPid().equals(pid)) {
                    list.remove(item);
                }
            }
        }
    }

    //3_清空购物车
    public void clearCart() {
        list.clear();
        total = 0;
    }

    //总计是经过计算获取
    public double getTotal() {
        total = 0;
        if (list.size() > 0) {
            for (CartItem item : list) {
                total = total + item.getSubTotal();
            }
        }
        return total;
    }


    public List<CartItem> getList() {
        return list;
    }

    public void setList(List<CartItem> list) {
        this.list = list;
    }


    public void setTotal(double total) {
        this.total = total;
    }


}
