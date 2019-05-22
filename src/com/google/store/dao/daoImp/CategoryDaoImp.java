package com.google.store.dao.daoImp;

import com.google.store.dao.CategoryDao;
import com.google.store.domain.Category;
import com.google.store.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class CategoryDaoImp implements CategoryDao {
    @Override
    public List<Category> getAllCats() throws Exception {
        String sql = "select * from category";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanListHandler<Category>(Category.class));
    }
}
