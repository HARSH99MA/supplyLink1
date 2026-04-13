package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.service.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() throws SQLException {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        if (suppliers == null || suppliers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<Supplier> getSupplierById(
            @PathVariable Integer supplierId) throws SQLException {
        Supplier supplier = supplierService.getSupplierById(supplierId);
        if (supplier == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(supplier, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> addSupplier(
            @RequestBody Supplier supplier) throws SQLException {
        Integer id = supplierService.addSupplier(supplier);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<Void> updateSupplier(
            @PathVariable Integer supplierId,
            @RequestBody Supplier supplier) throws SQLException {
        Supplier existing = supplierService.getSupplierById(supplierId);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        supplier.setSupplierId(supplierId);
        supplierService.updateSupplier(supplier);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<Void> deleteSupplier(
            @PathVariable Integer supplierId) throws SQLException {
        Supplier existing = supplierService.getSupplierById(supplierId);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        supplierService.deleteSupplier(supplierId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/fromArrayList")
    public ResponseEntity<List<Supplier>> getAllSuppliersFromArrayList() throws SQLException {
        List<Supplier> list = supplierService.getAllSuppliers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/toArrayList")
    public ResponseEntity<Integer> addSupplierToArrayList(
            @RequestBody Supplier supplier) throws SQLException {
        int id = supplierService.addSupplier(supplier);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/fromArrayList/all")
    public ResponseEntity<List<Supplier>> getAllSuppliersSortedByNameFromArrayList() throws SQLException {
        List<Supplier> sortedList = supplierService.getAllSuppliersSortedByName();
        return new ResponseEntity<>(sortedList, HttpStatus.OK);
    }
}
