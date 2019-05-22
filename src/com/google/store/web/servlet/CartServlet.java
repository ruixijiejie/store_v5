package com.google.store.web.servlet;

import com.google.store.domain.Cart;
import com.google.store.domain.CartItem;
import com.google.store.domain.Product;
import com.google.store.service.ProductService;
import com.google.store.service.serviceImp.ProductServiceImp;
import com.google.store.web.base.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartServlet extends BaseServlet {
    // addCartItemToCart
    public String addCartItemToCart(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 从session里获取购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null == cart) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        String pid = req.getParameter("pid");
        int num = Integer.parseInt(req.getParameter("quantity"));
        // 通过id查询购物项
        ProductService ProductService = new ProductServiceImp();
        Product product = ProductService.findProductByPid(pid);
        // 获取到代购买的购物项
        CartItem cartItem = new CartItem();
        cartItem.setNum(num);
        cartItem.setProduct(product);

        cart.addCartItemToCart(cartItem);
        resp.sendRedirect("/store_v5/jsp/cart.jsp");
        return null;
    }

    // removeCartItem
    public String removeCartItem(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String pid = req.getParameter("id");
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        cart.removeCartItem(pid);
        resp.sendRedirect("/store_v5/jsp/cart.jsp");
        return null;
    }

    // clearCart
    public String clearCart(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        cart.clearCart();
        resp.sendRedirect("/store_v5/jsp/cart.jsp");
        return null;
    }

}
