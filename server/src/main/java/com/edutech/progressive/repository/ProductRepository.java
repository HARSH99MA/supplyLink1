package com.edutech.progressive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.progressive.entity.Product;
import com.edutech.progressive.entity.Supplier;

public interface ProductRepository extends JpaRepository <Product , Integer> {
}
