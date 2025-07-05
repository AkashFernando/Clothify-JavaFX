package service.custom.impl;

import dto.Supplier;
import entity.SupplierEntity;
import org.modelmapper.ModelMapper;
import repository.custom.SupplierDao;
import repository.custom.impl.SupplierDaoImpl;
import service.custom.SupplierService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SupplierServiceImpl implements SupplierService {
    @Override
    public boolean saveSupplier(Supplier supplier) {
        SupplierEntity entity = new ModelMapper().map(supplier, SupplierEntity.class);

        SupplierDao supplierDao = new SupplierDaoImpl();

        try {
            return supplierDao.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        SupplierEntity entity = new ModelMapper().map(supplier, SupplierEntity.class);

        SupplierDao supplierDao = new SupplierDaoImpl();

        try {
            return supplierDao.update(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteSupplier(Supplier supplier) {
        SupplierDao supplierDao = new SupplierDaoImpl();

        try {
            return supplierDao.delete(supplier.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Supplier searchSupplier(Integer id) {
        SupplierDao supplierDao = new SupplierDaoImpl();
        SupplierEntity supplierEntity = supplierDao.search(id);

        if (supplierEntity == null) {
            return null;
        }
        return new ModelMapper().map(supplierEntity, Supplier.class);
    }

    @Override
    public List<Supplier> getAllSupplier() {
        SupplierDao supplierDao = new SupplierDaoImpl();
        List<SupplierEntity> list = supplierDao.getAll();

        return (list != null) ?
                list.stream()
                        .map(entity -> new ModelMapper().map(entity, Supplier.class))
                        .collect(Collectors.toList())
                : new ArrayList<>();
    }
}
