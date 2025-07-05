package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item {
    private Integer code;
    private String description;
    private Integer quantity;
    private Double prize;
    private String size;
    private String photoPath;
    private Supplier supplier;


}
