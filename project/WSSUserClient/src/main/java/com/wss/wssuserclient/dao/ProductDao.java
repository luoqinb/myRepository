package com.wss.wssuserclient.dao;


import com.wss.wssuserclient.mapper.ProductMapper;
import com.wss.wssuserclient.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ProductDao {

    public List<Product> getAllProducts();

    public int addProduct(Product product);

    public Product getProductById(int id);

    public Product getProductByPid(String pid);
}
