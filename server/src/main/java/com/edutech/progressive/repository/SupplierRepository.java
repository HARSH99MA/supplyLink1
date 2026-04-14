package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    @Transactional
    void deleteBySupplierId(int supplierId);

    Supplier findBySupplierId(int supplierId);
}
