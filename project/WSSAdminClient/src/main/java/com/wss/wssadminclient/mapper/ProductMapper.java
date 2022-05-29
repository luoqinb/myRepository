package com.wss.wssadminclient.mapper;

import com.wss.wssadminclient.model.Product;
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
    @Insert("insert into t_product (price, pid, description) values (#{price}, #{pid}, #{description});")
    public int addProduct(double price, String pid, String description);

    // Get a product by pid
    @Select("select * from t_product where pid=#{pid};")
    public Product getProductByPid(String pid);

    // Get product by id
    @Select("select * from t_product where id=#{id};")
    public Product getProductById(int id);
}
