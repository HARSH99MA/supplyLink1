package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Warehouse;
import com.edutech.progressive.service.impl.WarehouseServiceImplJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping({"/warehouse"})
public class WarehouseController {

    @Autowired
    WarehouseServiceImplJpa warehouseService;

    public WarehouseController() {
    }

    public WarehouseController(WarehouseServiceImplJpa warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() throws SQLException {
        return new ResponseEntity<>(warehouseService.getAllWarehouses(), HttpStatus.OK);
    }

    @GetMapping("/{warehouseId}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable int warehouseId) throws SQLException {
        return new ResponseEntity<>(warehouseService.getWarehouseById(warehouseId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> addWarehouse(@RequestBody Warehouse warehouse) throws SQLException {
        return new ResponseEntity<>(warehouseService.addWarehouse(warehouse), HttpStatus.CREATED);
    }

    @PutMapping("/{warehouseId}")
    public ResponseEntity<Void> updateWarehouse(@PathVariable int warehouseId, @RequestBody Warehouse warehouse) throws SQLException {
        warehouse.setWarehouseId(warehouseId);
        warehouseService.updateWarehouse(warehouse);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{warehouseId}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable int warehouseId) throws SQLException {
        warehouseService.deleteWarehouse(warehouseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<Warehouse>> getWarehousesBySupplier(@PathVariable int supplierId) throws SQLException {
        return new ResponseEntity<>(warehouseService.getWarehouseBySupplier(supplierId), HttpStatus.OK);
    }
}