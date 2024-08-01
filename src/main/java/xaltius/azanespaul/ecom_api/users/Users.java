package xaltius.azanespaul.ecom_api.users;

import jakarta.persistence.*;
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
    private String mobile;
    private String password;
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
