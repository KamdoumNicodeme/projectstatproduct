package net.dashboard.projectstatproduct.service;

import net.dashboard.projectstatproduct.model.Product;

import java.util.List;

public interface IProductService {
    Product createProduct(Product product) throws Exception;
    Product updateProduct(Product product,Long id) throws Exception;
    void deleteProduct(Long id);
    List<Product> getAllProducts();
}
