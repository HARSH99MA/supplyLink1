package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.repository.SupplierRepository;
import com.edutech.progressive.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SupplierServiceImplJpa implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImplJpa(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> getAllSuppliers() throws SQLException {
        return supplierRepository.getAllSuppliers();
    }

    @Override
    public int addSupplier(Supplier supplier) throws SQLException {
        return supplierRepository.addSupplier(supplier);
    }

    @Override
    public Supplier getSupplierById(int supplierId) throws SQLException {
        return supplierRepository.getSupplierById(supplierId);
    }

    @Override
    public List<Supplier> getAllSuppliersSortedByName() throws SQLException {
        return supplierRepository.getAllSuppliers();
    }

    @Override
    public void updateSupplier(Supplier supplier) throws SQLException {
        supplierRepository.updateSupplier(supplier);
    }

    @Override
    public void deleteSupplier(int supplierId) throws SQLException {
        supplierRepository.deleteSupplier(supplierId);
    }
}