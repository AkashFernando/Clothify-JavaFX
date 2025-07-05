package service.custom.impl;

import dto.Employee;
import dto.Item;
import entity.EmployeeEntity;
import entity.ItemEntity;
import org.modelmapper.ModelMapper;
import repository.custom.EmployeeDao;
import repository.custom.ItemDao;
import repository.custom.impl.EmployeeDaoImpl;
import repository.custom.impl.ItemDaoImpl;
import service.custom.ItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemServiceImpl implements ItemService {
    @Override
    public boolean saveItem(Item item) {
        ItemEntity entity = new ModelMapper().map(item, ItemEntity.class);

        ItemDao itemDao = new ItemDaoImpl();

        try {
            return itemDao.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateItem(Item item) {
        ItemEntity entity = new ModelMapper().map(item, ItemEntity.class);

        ItemDao itemDao = new ItemDaoImpl();

        try {
            return itemDao.update(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteItem(Item item) {
        ItemDao itemDao = new ItemDaoImpl();

        try {
            return itemDao.delete(item.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Item searchItem(Integer id) {
        ItemDao itemDao = new ItemDaoImpl();
        ItemEntity itemEntity = itemDao.search(id);

        if (itemEntity == null) {
            return null;
        }
        return new ModelMapper().map(itemEntity, Item.class);
    }

    @Override
    public List<Item> getAllItem() {
       ItemDao itemDao = new ItemDaoImpl();
        List<ItemEntity> list = itemDao.getAll();

        return (list != null) ?
                list.stream()
                        .map(entity -> new ModelMapper().map(entity, Item.class))
                        .collect(Collectors.toList())
                : new ArrayList<>();
    }
}
