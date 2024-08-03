package xaltius.azanespaul.ecom_api.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import xaltius.azanespaul.ecom_api.customer.Customer;
import xaltius.azanespaul.ecom_api.seller.Seller;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usersId;

    private String name;
    private String address;
    private String email;

    @NotEmpty(message = "Mobile is required.")
    private String mobile;

    @NotEmpty(message = "Password is required.")
    private String password;

    @NotEmpty(message = "Role is required.")
    private String role;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToOne(mappedBy = "users")
    private Seller seller;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToOne(mappedBy = "users")
    private Customer customer;
}
