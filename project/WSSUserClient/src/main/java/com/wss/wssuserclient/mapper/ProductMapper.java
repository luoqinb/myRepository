package com.wss.wssuserclient.mapper;

import com.wss.wssuserclient.model.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {

    // Get all products
    @Select("select * from t_product")
    public List<Product> getAllProducts();

    // Add a new product
    @Insert("insert into t_product (price, pid) values (#{price}, #{pid});")
    public int addProduct(double price, String pid);

    // Get product by id
    @Select("select * from t_product where id=#{id};")
    public Product getProductById(int id);

    // Get product by pid
    @Select("select * from t_product where pid=#{pid};")
    public Product getProductByPid(String pid);

}
