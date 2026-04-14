package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.service.impl.SupplierServiceImplArraylist;
import com.edutech.progressive.service.impl.SupplierServiceImplJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierServiceImplJpa supplierService;

    @Autowired
    SupplierServiceImplArraylist supplierServiceImplArraylist;

    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return new ResponseEntity<>(supplierService.getAllSuppliers(), HttpStatus.OK);
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable int supplierId) {
        return new ResponseEntity<>(supplierService.getSupplierById(supplierId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> addSupplier(@RequestBody Supplier supplier) {
        return new ResponseEntity<>(supplierService.addSupplier(supplier), HttpStatus.CREATED);
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<Void> updateSupplier(@PathVariable int supplierId, @RequestBody Supplier supplier) {
        supplier.setSupplierId(supplierId);
        supplierService.updateSupplier(supplier);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable int supplierId) {
        supplierService.deleteSupplier(supplierId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/fromArrayList")
    public ResponseEntity<List<Supplier>> getAllSuppliersFromArrayList() {
        return new ResponseEntity<>(supplierServiceImplArraylist.getAllSuppliers(), HttpStatus.OK);
    }

    @PostMapping({"/fromArrayList", "/toArrayList"})
    public ResponseEntity<Integer> addSupplierToArrayList(@RequestBody Supplier supplier) {
        return new ResponseEntity<>(supplierServiceImplArraylist.addSupplier(supplier), HttpStatus.CREATED);
    }

    @GetMapping("/fromArrayList/listAll")
    public ResponseEntity<List<Supplier>> getAllSuppliersSortedByNameFromArrayList() {
        return new ResponseEntity<>(supplierServiceImplArraylist.getAllSuppliersSortedByName(), HttpStatus.OK);
    }
}
