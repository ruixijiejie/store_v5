package com.google.store.service.serviceImp;

import com.google.store.dao.CategoryDao;
import com.google.store.dao.daoImp.CategoryDaoImp;
import com.google.store.domain.Category;
import com.google.store.service.CategoryService;

import java.util.List;

public class CategoryServiceImp  implements CategoryService {
    @Override
    public List<Category> getAllCats() throws Exception {
        CategoryDao CategoryDao = new CategoryDaoImp();
        return CategoryDao.getAllCats();
    }
}
