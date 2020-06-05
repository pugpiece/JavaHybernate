package Entities;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(
        name = "Shop"
)
public class Shop {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int shopId;

    @Column(
            name = "shopName",
            length = 50
    )
    private String shopName;

    @Column(
            name = "shopDescription",
            length = 10000
    )
    private String shopDescription;

    @Column(
            name = "shopRating",
            length = 5
    )
    private float shopRating;

    @ManyToMany
    @JoinTable(
            name = "ProductToShop",
            joinColumns = {@JoinColumn(
                    name = "shopId"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "productId"
            )}
    )
    private Set<Product> products;

    @ManyToMany
    @JoinTable(
            name = "InstrumentToShop",
            joinColumns = {@JoinColumn(
                    name = "shopId"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "instrumentId"
            )}
    )
    private Set<Instrument> instruments;

    public Shop() {
    }

    public int getShopId() { return this.shopId; }
    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() { return this.shopName; }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopDescription () { return this.shopDescription; }
    public void setShopDescription(String shopDescription) {
        this.shopDescription = shopDescription;
    }

    public float getShopRating() {
        return this.shopRating;
    }
    public void setShopRating(float shopRating) {
        this.shopRating = shopRating;
    }

    public Set<Product> getProducts() { return this.products; }
    public void setProducts(Set<Product> products) { this.products = products; }

    public Set<Instrument> getInstruments() { return this.instruments; }
    public void setInstruments(Set<Instrument> instruments) { this.instruments = instruments; }
}
