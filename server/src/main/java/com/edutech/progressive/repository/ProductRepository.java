package com.edutech.progressive.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.progressive.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByProductId(int productId);

    @Transactional
    void deleteByProductId(int productId);

    List<Product> findByWarehouse_WarehouseId(int warehouseId);
}


