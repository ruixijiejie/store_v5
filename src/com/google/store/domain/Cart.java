package com.google.store.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    //代表购物车上的个数不确定的购物项,键:商品pid,值:购物项
    private double total;

    Map<String, CartItem> map = new HashMap<String, CartItem>();

    public void addCartItemToCart(CartItem cartItem) {
        String pid = cartItem.getProduct().getPid();
        if (map.containsKey(pid)) {
            CartItem oldItem = map.get(pid);
            oldItem.setNum(oldItem.getNum() + cartItem.getNum());
        } else {
            map.put(pid, cartItem);
        }
    }

    public void removeCartItem(String pid) {
        map.remove(pid);
    }

    public void clearCart() {
        map.clear();
    }

    //计算总计
    public double getTotal() {
        //让总计清零
        total = 0;
        // 获取map中所有的购物项
        Collection<CartItem> values = map.values();
        // 遍历所有购物项
        for (CartItem cartItem : values) {
            total += cartItem.getSubTotal();
        }
        return total;
    }

    //为了方便遍历MAP中的所有的购物项,提供以下方法

    public Collection<CartItem> getCartItems() {
        return map.values();
    }

    public Map<String, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<String, CartItem> map) {
        this.map = map;
    }

}
