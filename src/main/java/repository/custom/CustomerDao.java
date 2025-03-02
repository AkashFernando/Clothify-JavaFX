package repository.custom;

import entity.CustomerEntity;
import repository.CrudRepository;

import java.util.List;

public interface CustomerDao extends CrudRepository<CustomerEntity,String> {
    List<String> getIDs();
}
