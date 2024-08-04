package xaltius.azanespaul.ecom_api.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xaltius.azanespaul.ecom_api.seller.Seller;

import java.io.Serializable;

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
}
