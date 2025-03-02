package dto;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Employee {
    private String name;
    private Integer id;
    private String contactNumber;
    private String email;
    private String address;
    private String password;
    private String role;
}
