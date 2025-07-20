package service.custom;

import dto.Employee;

import java.util.List;

public interface EmployeeService {

    boolean saveEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    boolean deleteEmployee(Employee employee);
    Employee searchEmployee(Integer id);
    List<Employee> getAllEmployee();
    List<Employee> getAdmins();
}
