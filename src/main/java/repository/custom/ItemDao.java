package repository.custom;
import entity.ItemEntity;

import java.util.List;

public interface ItemDao {
    boolean save(ItemEntity itemEntity);
    boolean update(ItemEntity itemEntity);
    boolean delete(Integer id);
    ItemEntity search(Integer id);
    List<ItemEntity> getAll();
}
