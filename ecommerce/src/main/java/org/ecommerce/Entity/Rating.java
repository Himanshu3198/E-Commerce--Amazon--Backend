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
    private String review;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;


//    Getter and Setter
    public Long getId() {
        return id;
    }

    public Long getRating() {
        return rating;
    }
    public User getUser() {return user;}

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

    public Rating setUser(User user){
        this.user = user;
        return this;
    }


}
