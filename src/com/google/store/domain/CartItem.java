package com.google.store.domain;

public class CartItem {
    private Product product; //商品对象
    private int num; //数量
    private double subTotal; //小计

    public CartItem(Product product, int num, double subTotal) {
        this.product = product;
        this.num = num;
        this.subTotal = subTotal;
    }

    public CartItem() {

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getSubTotal() {
        return product.getShop_price() * num;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}
