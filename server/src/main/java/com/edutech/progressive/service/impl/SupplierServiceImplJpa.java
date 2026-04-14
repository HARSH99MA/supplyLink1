package com.edutech.progressive.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.repository.SupplierRepository;
import com.edutech.progressive.service.SupplierService;

@Service
public class SupplierServiceImplJpa implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public SupplierServiceImplJpa() {
    }

    public SupplierServiceImplJpa(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public int addSupplier(Supplier supplier) {
        Supplier savedSupplier = supplierRepository.save(supplier);
        return savedSupplier.getSupplierId();
    }

    @Override
    public List<Supplier> getAllSuppliersSortedByName() {
        List<Supplier> suppliers = supplierRepository.findAll();
        Collections.sort(suppliers, Comparator.comparing(Supplier::getSupplierName));
        return suppliers;
    }

    @Override
    public Supplier getSupplierById(int supplierId) {
        return supplierRepository.findBySupplierId(supplierId);
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    @Override
    public void deleteSupplier(int supplierId) {
        supplierRepository.deleteBySupplierId(supplierId);
    }
}
