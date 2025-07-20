package service.custom.impl;

import dto.Customer;
import dto.Employee;
import entity.CustomerEntity;
import entity.EmployeeEntity;
import org.modelmapper.ModelMapper;
import repository.custom.CustomerDao;
import repository.custom.EmployeeDao;
import repository.custom.impl.CustomerDaoImpl;
import repository.custom.impl.EmployeeDaoImpl;
import service.custom.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    @Override
    public boolean saveCustomer(Customer customer) {
       CustomerEntity entity= new ModelMapper().map(customer, CustomerEntity.class);

        CustomerDao customerDao = new CustomerDaoImpl();

        try {
            return customerDao.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        CustomerEntity entity = new ModelMapper().map(customer, CustomerEntity.class);

        CustomerDao customerDao = new CustomerDaoImpl();

        try {
            return customerDao.update(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCustomer(Customer customer) {
        CustomerDao customerDao = new CustomerDaoImpl();

        try {
            return customerDao.delete(customer.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Customer searchCustomer(Integer id) {
        CustomerDao customerDao = new CustomerDaoImpl();
        CustomerEntity customerEntity = customerDao.search(id);

        if (customerEntity == null) {
            return null;
        }
        return new ModelMapper().map(customerEntity, Customer.class);
    }

    @Override
    public List<Customer> getAllCustomer() {
        CustomerDao customerDao = new CustomerDaoImpl();
        List<CustomerEntity> list = customerDao.getAll();

        return (list != null) ?
                list.stream()
                        .map(entity -> new ModelMapper().map(entity, Customer.class))
                        .collect(Collectors.toList())
                : new ArrayList<>();
    }
}
