package com.google.store.web.servlet;

import com.google.store.domain.Product;
import com.google.store.service.ProductService;
import com.google.store.service.serviceImp.ProductServiceImp;
import com.google.store.web.base.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class IndexServlet extends BaseServlet {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取全部的分类信息
        //CategoryService CategoryService = new CategoryServiceImp();
        //List<Category> list = CategoryService.getAllCats();
        // 返回的集合放到request
        //req.setAttribute("allCats", list);

        //调用业务层查询最新商品,查询最热商品,返回2个集合
        ProductService ProductService = new ProductServiceImp();
        List<Product> list01 = ProductService.findHots();
        List<Product> list02 = ProductService.findNews();
        //将2个集合放入到request
        req.setAttribute("hots", list01);
        req.setAttribute("news", list02);
        // 转发到真实的首页
        return "/jsp/index.jsp";
    }
}
