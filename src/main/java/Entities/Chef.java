package Entities;

import javax.persistence.*;


@Entity
@Table(
        name = "Chef"
)
public class Chef extends Customer{

    @Column(
            name = "chefRating",
            length = 5
    )
    private float chefRating;

    public Chef() {
    }

    public Chef(String login, String password) {
        super(login,password);
        chefRating = 0;
    }

    public float getChefRating() { return this.chefRating; }
    public void setChefRating(float chefRating) {
        this.chefRating = chefRating;
    }
}
