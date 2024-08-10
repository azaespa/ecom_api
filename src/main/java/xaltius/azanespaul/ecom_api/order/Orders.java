package xaltius.azanespaul.ecom_api.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xaltius.azanespaul.ecom_api.cart.Cart;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ordersId;

    private String transactionId;
    private String dateOrdered;
    private String status;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", referencedColumnName = "cartId")
    private Cart cart;
}
