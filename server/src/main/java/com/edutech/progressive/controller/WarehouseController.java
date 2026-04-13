package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Warehouse;
import com.edutech.progressive.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() throws SQLException {
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        if (warehouses == null || warehouses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(warehouses, HttpStatus.OK);
    }

    @GetMapping("/{warehouseId}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable int warehouseId) throws SQLException {
        Warehouse warehouse = warehouseService.getWarehouseById(warehouseId);
        if (warehouse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> addWarehouse(@RequestBody Warehouse warehouse) throws SQLException {
        Integer id = warehouseService.addWarehouse(warehouse);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping("/{warehouseId}")
    public ResponseEntity<Void> updateWarehouse(
            @PathVariable int warehouseId,
            @RequestBody Warehouse warehouse) throws SQLException {

        Warehouse existing = warehouseService.getWarehouseById(warehouseId);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        warehouse.setWarehouseId(warehouseId);
        warehouseService.updateWarehouse(warehouse);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{warehouseId}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable int warehouseId) throws SQLException {
        Warehouse existing = warehouseService.getWarehouseById(warehouseId);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        warehouseService.deleteWarehouse(warehouseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<Warehouse>> getWarehousesBySupplier(
            @PathVariable int supplierId) throws SQLException {

        List<Warehouse> warehouses = warehouseService.getWarehouseBySupplier(supplierId);
        if (warehouses == null || warehouses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(warehouses, HttpStatus.OK);
    }
}
