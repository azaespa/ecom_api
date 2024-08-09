package xaltius.azanespaul.ecom_api.product;

import jakarta.persistence.*;
import lombok.*;
import xaltius.azanespaul.ecom_api.cart.Cart;
import xaltius.azanespaul.ecom_api.seller.Seller;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    private String name;
    private String description;
    private String imageUrl;
    private String category;
    private int quantity;
    private int price;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false, updatable = false)
    private Seller seller;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "product")
    private List<Cart> cart;
}
