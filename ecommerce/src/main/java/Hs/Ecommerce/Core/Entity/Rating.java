package Hs.Ecommerce.Core.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rating_review")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating")
    private Long rating;

    @Column(name = "review")
    private String review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    // Getter and Setter
    public Long getId() {
        return id;
    }

    public Long getRating() {
        return rating;
    }

    public Rating setRating(Long rating) {
        this.rating = rating;
        return this;
    }

    public String getReview() {
        return review;
    }

    public Rating setReview(String review) {
        this.review = review;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Rating setUser(User user) {
        this.user = user;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public Rating setProduct(Product product) {
        this.product = product;
        return this;
    }
}
