package com.wss.wssadminclient.dao;


import com.wss.wssadminclient.model.Product;

import java.util.List;

public interface ProductDao {

    public List<Product> getAllProducts();

    public int addProduct(Product product);

    public Product getProductByPid(String pid);

    public Product getProductById(int id);
}
