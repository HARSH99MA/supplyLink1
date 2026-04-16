package com.edutech.progressive.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.exception.SupplierAlreadyExistsException;
import com.edutech.progressive.exception.SupplierDoesNotExistException;
import com.edutech.progressive.repository.SupplierRepository;
import com.edutech.progressive.service.SupplierService;

@Service
public class SupplierServiceImplJpa implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImplJpa(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public int addSupplier(Supplier supplier) {

        if (supplier.getRole() == null ||
                (!supplier.getRole().equals("USER") && !supplier.getRole().equals("ADMIN"))) {
            throw new IllegalArgumentException("Role must be USER or ADMIN");
        }

        if (supplierRepository.findByUsername(supplier.getUsername()) != null) {
            throw new SupplierAlreadyExistsException("Username already exists");
        }

        if (supplierRepository.findByEmail(supplier.getEmail()) != null) {
            throw new SupplierAlreadyExistsException("Email already exists");
        }

        Supplier savedSupplier = supplierRepository.save(supplier);
        return savedSupplier.getSupplierId();
    }

    @Override
    public List<Supplier> getAllSuppliersSortedByName() {
        List<Supplier> suppliers = supplierRepository.findAll();
        Collections.sort(suppliers);
        return suppliers;
    }

    @Override
    public void updateSupplier(Supplier supplier) {

        Supplier existingSupplier =
                supplierRepository.findBySupplierId(supplier.getSupplierId());

        if (existingSupplier == null) {
            throw new SupplierDoesNotExistException("Supplier does not exist");
        }

        if (supplier.getRole() == null ||
                (!supplier.getRole().equals("USER") && !supplier.getRole().equals("ADMIN"))) {
            throw new IllegalArgumentException("Role must be USER or ADMIN");
        }

        Supplier byUsername = supplierRepository.findByUsername(supplier.getUsername());
        if (byUsername != null &&
                byUsername.getSupplierId() != supplier.getSupplierId()) {
            throw new SupplierAlreadyExistsException("Username already exists");
        }

        Supplier byEmail = supplierRepository.findByEmail(supplier.getEmail());
        if (byEmail != null &&
                byEmail.getSupplierId() != supplier.getSupplierId()) {
            throw new SupplierAlreadyExistsException("Email already exists");
        }

        existingSupplier.setSupplierName(supplier.getSupplierName());
        existingSupplier.setEmail(supplier.getEmail());
        existingSupplier.setPhone(supplier.getPhone());
        existingSupplier.setAddress(supplier.getAddress());
        existingSupplier.setUsername(supplier.getUsername());
        existingSupplier.setPassword(supplier.getPassword());
        existingSupplier.setRole(supplier.getRole());

        supplierRepository.save(existingSupplier);
    }

    @Override
    @Transactional
    public void deleteSupplier(int supplierId) {
        if (!supplierRepository.existsById(supplierId)) {
            throw new SupplierDoesNotExistException("Supplier does not exist");
        }
        supplierRepository.deleteById(supplierId);
    }

    @Override
    public Supplier getSupplierById(int supplierId) {
        Supplier supplier = supplierRepository.findBySupplierId(supplierId);
        if (supplier == null) {
            throw new SupplierDoesNotExistException("Supplier does not exist");
        }
        return supplier;
    }
}

