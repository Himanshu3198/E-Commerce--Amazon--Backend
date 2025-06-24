package org.ecommerce.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rating_review")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating",nullable = true)
    private Long rating;

    @Column(name = "review",nullable = true)
    private Long review;

//    Getter and Setter
    public Long getId() {
        return id;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public Long getReview() {
        return review;
    }

    public void setReview(Long review) {
        this.review = review;
    }
}
