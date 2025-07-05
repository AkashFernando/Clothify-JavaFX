package service.custom;

import dto.Employee;
import dto.Supplier;

import java.util.List;

public interface SupplierService {
    boolean saveSupplier(Supplier supplier);
    boolean updateSupplier(Supplier supplier);
    boolean deleteSupplier(Supplier supplier);
    Supplier searchSupplier(Integer id);
    List<Supplier> getAllSupplier();
}
