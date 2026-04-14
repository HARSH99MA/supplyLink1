package com.edutech.progressive.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Product;
import com.edutech.progressive.repository.ProductRepository;
import com.edutech.progressive.service.ProductService;

@Service
public class ProductServiceImplJpa implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    //------
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int productId) {
        return productRepository.findByProductId(productId);
    }

    @Override
    public int addProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct.getProductId();
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(int productId) {
        productRepository.deleteByProductId(productId);
    }

    @Override
    public List<Product> getAllProductByWarehouse(int warehouseId) {
        return productRepository.findByWarehouse_WarehouseId(warehouseId);
    }
}
