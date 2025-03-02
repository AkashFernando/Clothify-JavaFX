package service.custom.impl;

import dto.Employee;
import entity.EmployeeEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.EmployeeDao;
import repository.custom.impl.EmployeeDaoImpl;
import service.custom.EmployeeService;
import util.DaoType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {


    @Override
    public boolean saveEmployee(Employee employee) {
        EmployeeEntity entity = new ModelMapper().map(employee, EmployeeEntity.class);

        EmployeeDao employeeDao = new EmployeeDaoImpl();

        try {
            return employeeDao.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        EmployeeEntity entity = new ModelMapper().map(employee, EmployeeEntity.class);

        EmployeeDao employeeDao = new EmployeeDaoImpl();

        try {
            return employeeDao.update(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteEmployee(Employee employee) {
        EmployeeDao employeeDao = new EmployeeDaoImpl();

        try {
            return employeeDao.delete(employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Employee searchEmployee(Integer id) {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        EmployeeEntity employeeEntity = employeeDao.search(id);

        if (employeeEntity == null) {
            return null;
        }
        return new ModelMapper().map(employeeEntity, Employee.class);
    }

    @Override
    public List<Employee> getAllEmployee() {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        List<EmployeeEntity> list = employeeDao.getAll();

        return (list != null) ?
                list.stream()
                        .map(entity -> new ModelMapper().map(entity, Employee.class))
                        .collect(Collectors.toList())
                : new ArrayList<>();
    }

    @Override
    public List<Employee> getAdmins() {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        List<EmployeeEntity> list = employeeDao.getAllAdmins();

        return (list != null) ?
                list.stream()
                        .map(entity -> new ModelMapper().map(entity, Employee.class))
                        .collect(Collectors.toList())
                : new ArrayList<>();
    }
}
