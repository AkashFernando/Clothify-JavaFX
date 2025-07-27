package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private Integer orderID;
    private String orderDate;
    private String orderTime;
    private Double amount;
    private Customer customer;
}
