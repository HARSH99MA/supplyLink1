package com.edutech.progressive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.progressive.dao.WarehouseDAO;
import com.edutech.progressive.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse , Integer> , WarehouseDAO{

    Warehouse findByWarehouseId(int warehouseId);


}
