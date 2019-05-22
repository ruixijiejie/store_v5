package com.google.store.dao;

import com.google.store.domain.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getAllCats() throws Exception;
}
