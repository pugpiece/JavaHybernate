package Entities;

import javax.persistence.*;


@Entity
@Table(
        name = "Country"
)
public class Country {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int countryId;

    @Column(
            name = "countryName",
            length = 50
    )
    private String countryName;

    @Column(
            name = "countryDescription",
            length = 10000
    )
    private String countryDescription;

    public Country() {
    }

    public int getCountryId() { return this.countryId; }
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() { return this.countryName; }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryDescription () { return this.countryDescription; }
    public void setCountryDescription(String countryDescription) {
        this.countryDescription = countryDescription;
    }
}
