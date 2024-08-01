package xaltius.azanespaul.ecom_api.seller;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xaltius.azanespaul.ecom_api.users.Users;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seller implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sellerId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id", referencedColumnName = "usersId")
    private Users users;

}
