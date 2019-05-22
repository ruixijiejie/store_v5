package com.google.store.dao;

import com.google.store.domain.Product;
import java.util.List;

public interface ProductDao {
    List<Product> findHots() throws Exception;

    List<Product> findNews() throws Exception;

    Product findProductByPid(String pid) throws Exception;

    int findTotalNum(String cid) throws Exception;

    List<Product> findProductsWithCidAndPage(String cid, int startIndex, int pageSize) throws Exception;
}
