package service.custom;
import dto.Customer;
import service.SuperService;

import java.util.List;

public interface CustomerService extends SuperService {
    boolean saveCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(Customer customer);
    Customer searchCustomer(Integer id);
    List<Customer> getAllCustomer();
}
