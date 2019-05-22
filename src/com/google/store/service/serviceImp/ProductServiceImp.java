package com.google.store.service.serviceImp;

import com.google.store.dao.ProductDao;
import com.google.store.dao.daoImp.ProductDaoImp;
import com.google.store.domain.Product;
import com.google.store.service.ProductService;
import com.google.store.utils.PageModel;

import java.util.List;

public class ProductServiceImp implements ProductService {
    ProductDao ProductDao = new ProductDaoImp();

    @Override
    public List<Product> findHots() throws Exception {
        return ProductDao.findHots();
    }

    @Override
    public List<Product> findNews() throws Exception {
        return ProductDao.findNews();
    }

    @Override
    public Product findProductByPid(String pid) throws Exception {
        return ProductDao.findProductByPid(pid);
    }

    @Override
    public PageModel findProductsWithCidAndPage(String cid, int curNum) throws Exception {
        //1_创建PageModel对象,目的:携带分页参数 select count(*) from product where cid=?
        int totalRecords = ProductDao.findTotalNum(cid);
        PageModel pm = new PageModel(curNum, totalRecords, 12);
        //2_关联集合 SELECT * FROM product WHERE cid=1 LIMIT (当前页-1)*5,5;
        List<Product> list = ProductDao.findProductsWithCidAndPage(cid, pm.getStartIndex(), pm.getPageSize());
        pm.setList(list);
        //3_关联url
        pm.setUrl("ProductServlet?method=findProductsWithCidAndPage&cid=" + cid);
        return pm;
    }
}
