package com.google.store.web.servlet;

import com.google.store.domain.Category;
import com.google.store.service.CategoryService;
import com.google.store.service.serviceImp.CategoryServiceImp;
import com.google.store.utils.JedisUtils;
import com.google.store.web.base.BaseServlet;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CategoryServlet extends BaseServlet {
    // findAllCats
    public String findAllCats(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 在redis中获取全部分类信息
        Jedis jedis = JedisUtils.getJedis();
        String jsonStr = jedis.get("allCats");
        if (null == jsonStr || "".equals(jsonStr)) {
            // 调取业务层全部分类
            CategoryService CategoryService = new CategoryServiceImp();
            List<Category> list = CategoryService.getAllCats();
            // 将全部分类转换为json格式的数据
            jsonStr = JSONArray.fromObject(list).toString();
            System.out.println(jsonStr);
            // 存入redis
            jedis.set("allCats", jsonStr);
            System.out.println("redis缓存中没有数据");
            // 将全部分类相应到客户端
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().print(jsonStr);
        } else {
            System.out.println("redis缓存中有数据");
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().print(jsonStr);
        }
        JedisUtils.closeJedis(jedis);
        return null;
    }
}
