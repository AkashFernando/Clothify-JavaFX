package service.custom;


import dto.Item;

import java.util.List;

public interface ItemService {
    boolean saveItem(Item item);
    boolean updateItem(Item item);
    boolean deleteItem(Item item);
    Item searchItem(Integer id);
    List<Item> getAllItem();


}
