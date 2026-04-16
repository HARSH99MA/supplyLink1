package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.service.impl.SupplierServiceImplArraylist;
import com.edutech.progressive.service.impl.SupplierServiceImplJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierServiceImplArraylist supplierServiceImplArraylist;

    @Autowired
    private SupplierServiceImplJpa supplierServiceImplJpa;

    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return ResponseEntity.ok(supplierServiceImplJpa.getAllSuppliers());
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable int supplierId) {
        return ResponseEntity.ok(supplierServiceImplJpa.getSupplierById(supplierId));
    }

    @PostMapping
    public ResponseEntity<Integer> addSupplier(@RequestBody Supplier supplier) {
        return ResponseEntity.status(201)
                .body(supplierServiceImplJpa.addSupplier(supplier));
    }

    @PutMapping
    public ResponseEntity<Void> updateSupplier(@RequestBody Supplier supplier) {
        supplierServiceImplJpa.updateSupplier(supplier);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable int supplierId) {
        supplierServiceImplJpa.deleteSupplier(supplierId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/fromArrayList")
    public ResponseEntity<List<Supplier>> getAllSuppliersFromArrayList() {
        return ResponseEntity.ok(
                supplierServiceImplArraylist.getAllSuppliers());
    }

    @PostMapping("/toArrayList")
    public ResponseEntity<Integer> addSupplierToArrayList(@RequestBody Supplier supplier) {
        return ResponseEntity.status(201)
                .body(supplierServiceImplArraylist.addSupplier(supplier));
    }

    @GetMapping("/fromArrayList/all")
    public ResponseEntity<List<Supplier>> getAllSuppliersSortedByNameFromArrayList() {
        return ResponseEntity.ok(
                supplierServiceImplArraylist.getAllSuppliersSortedByName());
    }
}