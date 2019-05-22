package com.google.store.service;

import com.google.store.domain.Product;
import com.google.store.utils.PageModel;

import java.util.List;

public interface ProductService {
    List<Product> findHots() throws Exception;

    List<Product> findNews() throws Exception;

    Product findProductByPid(String pid) throws Exception;

    PageModel findProductsWithCidAndPage(String cid, int curNum) throws Exception;
}
