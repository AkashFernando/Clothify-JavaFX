package repository.custom;

import entity.EmployeeEntity;

import java.util.List;

public interface EmployeeDao {

    boolean save(EmployeeEntity employeeEntity);
    boolean update(EmployeeEntity employeeEntity);
    boolean delete(Integer id);
    EmployeeEntity search(Integer id);
    List<EmployeeEntity>getAll();
    List<EmployeeEntity> getAllAdmins();
}
