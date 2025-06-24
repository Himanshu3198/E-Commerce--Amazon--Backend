package org.ecommerce.Entity;


import jakarta.persistence.*;
import org.ecommerce.Enum.AvailabilityStatus;
import org.ecommerce.Enum.Category;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name",nullable = false)
    private String productName;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "price",nullable = false)
    private Double price;

    @Column(name = "category",nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "availability",nullable = false)
    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus;

    @OneToMany()
    @Column(name = "rating_id")
    private Rating rating;

//    Getter and Setter


    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public AvailabilityStatus getAvailabilityStatus() {
        return availabilityStatus;
    }

    public Rating getRating() {
        return rating;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}
