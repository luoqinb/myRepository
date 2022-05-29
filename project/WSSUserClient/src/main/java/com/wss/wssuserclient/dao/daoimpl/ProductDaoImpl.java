package com.wss.wssuserclient.dao.daoimpl;


import com.wss.wssuserclient.dao.ProductDao;
import com.wss.wssuserclient.mapper.ProductMapper;
import com.wss.wssuserclient.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productDao")
public class ProductDaoImpl implements ProductDao {

    @Autowired private ProductMapper productMapper;

    @Override
    public List<Product> getAllProducts() {
        return productMapper.getAllProducts();
    }

    @Override
    public int addProduct(Product product) {
        return productMapper.addProduct(product.getPrice(), product.getPid());
    }

    @Override
    public Product getProductById(int id) {
        return productMapper.getProductById(id);
    }

    @Override
    public Product getProductByPid(String pid) {
        return productMapper.getProductByPid(pid);
    }
}
