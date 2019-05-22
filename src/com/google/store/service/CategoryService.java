package com.google.store.service;

import com.google.store.domain.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCats() throws Exception;
}
