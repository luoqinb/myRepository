package com.wss.wssadminclient.dao.impl;


import com.wss.wssadminclient.dao.ProductDao;
import com.wss.wssadminclient.mapper.ProductMapper;
import com.wss.wssadminclient.model.Product;
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
        return productMapper.addProduct(product.getPrice(), product.getPid(), product.getDescription());
    }

    @Override
    public Product getProductByPid(String pid) {
        return productMapper.getProductByPid(pid);
    }

    @Override
    public Product getProductById(int id) {
        return productMapper.getProductById(id);
    }
}
