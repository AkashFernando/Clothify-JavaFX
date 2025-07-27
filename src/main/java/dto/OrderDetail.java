package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetail {
    private Integer orderID;
    private String itemCode;
    private Integer orderQTY;
    private Double unitPrice;
}
