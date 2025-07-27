package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "OrderDetail")
public class OrderDetailEntity {
    private Integer orderID;
    private String itemCode;
    private Integer orderQTY;
    private Double unitPrice;
}
