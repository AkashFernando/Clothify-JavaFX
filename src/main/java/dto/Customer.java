package dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Customer {
    private String name;
    private String id;
    private String contactNumber;
    private String email;
    private String address;
}
