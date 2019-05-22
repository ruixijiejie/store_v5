package com.google.store.web.servlet;

import com.google.store.domain.Product;
import com.google.store.service.ProductService;
import com.google.store.service.serviceImp.ProductServiceImp;
import com.google.store.utils.PageModel;
import com.google.store.web.base.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductServlet extends BaseServlet {
    //findProductByPid
    public String findProductByPid(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取商品pid
        String pid = req.getParameter("pid");
        // 根据商品pid查询商品信息
        ProductService ProductService = new ProductServiceImp();
        Product product = ProductService.findProductByPid(pid);
        // 将获取的商品放到request中
        req.setAttribute("product", product);
        // 转发到真实的页面
        return "/jsp/product_info.jsp";
    }

    //findProductsWithCidAndPage
    public String findProductsWithCidAndPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //接受当前页
        int curNum = Integer.parseInt(req.getParameter("num"));
        //接受分类cid
        String cid = req.getParameter("cid");
        //调用业务层查询当前分类下的当前页数据功能,返回PageModel对象
        ProductService ProductService = new ProductServiceImp();
        PageModel pm = ProductService.findProductsWithCidAndPage(cid, curNum);
        //将PageModel对象存入到request
        req.setAttribute("page", pm);
        //转发到"/jsp/product_list.jsp"
        return "/jsp/product_list.jsp";
    }
}
