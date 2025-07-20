package repository.custom;

import entity.CustomerEntity;


import java.util.List;

public interface CustomerDao {
    boolean save(CustomerEntity customerEntity);
    boolean update(CustomerEntity customerEntity);
    boolean delete(Integer id);
    CustomerEntity search(Integer id);
    List<CustomerEntity>getAll();
}
