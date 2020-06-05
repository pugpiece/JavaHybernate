package Entities;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(
        name = "Instrument"
)
public class Instrument {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int instrumentId;

    @Column(
            name = "instrumentName",
            length = 50
    )
    private String instrumentName;

    @Column(
            name = "instrumentDescription",
            length = 10000
    )
    private String instrumentDescription;

    @Column(
            name = "instrumentCost",
            length = 100000
    )
    private float instrumentCost;

    @ManyToMany
    @JoinTable(
            name = "InstrumentToDish",
            joinColumns = {@JoinColumn(
                    name = "instrumentId"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "dishId"
            )}
    )
    private Set<Dish> dishes;

    @ManyToMany
    @JoinTable(
            name = "InstrumentToShop",
            joinColumns = {@JoinColumn(
                    name = "instrumentId"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "shopId"
            )}
    )
    private Set<Shop> shops;

    public Instrument() {
    }

    public int getInstrumentId() {
        return this.instrumentId;
    }
    public void setInstrumentId(int instrumentId) { this.instrumentId = instrumentId; }

    public String getInstrumentName() {
        return this.instrumentName;
    }
    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public String getInstrumentDescription () { return this.instrumentDescription; }
    public void setInstrumentDescription(String instrumentDescription) { this.instrumentDescription = instrumentDescription; }

    public float getInstrumentCost() { return this.instrumentCost; }
    public void setInstrumentCost(float instrumentCost) {
        this.instrumentCost = instrumentCost;
    }

    public Set<Dish> getDishes() {
        return this.dishes;
    }
    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public Set<Shop> getShops() {
        return this.shops;
    }
    public void setShops(Set<Shop> shops) {
        this.shops = shops;
    }

}
