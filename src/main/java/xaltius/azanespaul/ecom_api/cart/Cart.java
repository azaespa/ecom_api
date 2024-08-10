package xaltius.azanespaul.ecom_api.cart;

import jakarta.persistence.*;
import lombok.*;
import xaltius.azanespaul.ecom_api.customer.Customer;
import xaltius.azanespaul.ecom_api.order.Orders;
import xaltius.azanespaul.ecom_api.product.Product;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @ManyToOne(cascade = CascadeType.MERGE , fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Product product;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "cart")
    private List<Orders> orders;

    private int isActive;
}
