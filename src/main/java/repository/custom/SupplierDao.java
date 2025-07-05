package repository.custom;

import entity.EmployeeEntity;
import entity.SupplierEntity;

import java.util.List;

public interface SupplierDao {
    boolean save(SupplierEntity supplierEntity);
    boolean update(SupplierEntity supplierEntity);
    boolean delete(Integer id);
    SupplierEntity search(Integer id);
    List<SupplierEntity> getAll();
}
