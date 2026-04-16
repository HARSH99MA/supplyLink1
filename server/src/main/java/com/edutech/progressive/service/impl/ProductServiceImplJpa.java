package com.edutech.progressive.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Product;
import com.edutech.progressive.repository.ProductRepository;
import com.edutech.progressive.service.ProductService;

@Service
public class ProductServiceImplJpa implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImplJpa(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public int addProduct(Product product) {
        return productRepository.save(product).getProductId();
    }

    @Override
    public void updateProduct(Product product) {
        if (productRepository.existsById(product.getProductId())) {
            productRepository.save(product);
        }
    }

    @Override
    public void deleteProduct(int productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
        }
    }

    @Override
    public List<Product> getAllProductByWarehouse(int warehouseId) {
        return productRepository.findAllByWarehouse_WarehouseId(warehouseId);
    }
}
