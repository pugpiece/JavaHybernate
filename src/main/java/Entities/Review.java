package Entities;

import javax.persistence.*;


@Entity
@Table(
        name = "Review"
)
public class Review {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int reviewId;

    @Column(
            name = "reviewRating",
            length = 5
    )
    private float reviewRating;

    @Column(
            name = "reviewPluses",
            length = 50
    )
    private String reviewPluses;

    @Column(
            name = "reviewMinuses",
            length = 50
    )
    private String reviewMinuses;

    @Column(
            name = "reviewOverview",
            length = 50
    )
    private String reviewOverview;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "customerId"
    )
    private Customer customer;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "dishId"
    )
    private Dish dish;

    public Review() {
    }

    public int getReviewId() { return this.reviewId; }
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public float getReviewRating() { return this.reviewRating; }
    public void setReviewRating(float reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getReviewPluses() { return this.reviewPluses; }
    public void setReviewPluses(String reviewPluses) {
        this.reviewPluses = reviewPluses;
    }

    public String getReviewMinuses() { return this.reviewMinuses; }
    public void setReviewMinuses(String reviewMinuses) {
        this.reviewMinuses = reviewMinuses;
    }

    public String getReviewOverview() { return this.reviewOverview; }
    public void setReviewOverview(String reviewOverview) {
        this.reviewOverview = reviewOverview;
    }

    public Customer getCustomer() { return this.customer; }
    public void setCustomer(Customer reviewOverview) {
        this.customer = customer;
    }

    public Dish getDish() { return this.dish; }
    public void setDish(Dish dish) {
        this.dish = dish;
    }


}
