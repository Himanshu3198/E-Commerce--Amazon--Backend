package Hs.Ecommerce.Core.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import Hs.Ecommerce.Core.Enum.AvailabilityStatus;
import Hs.Ecommerce.Core.Enum.Category;

import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "category", nullable = false,length = 255)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "availability", nullable = false)
    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus;

    @Column(name = "image_url", unique = true, nullable = true)
    private String image;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Rating> ratings;

    // Getters


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

    public String getImage() {
        return image;
    }

    public Long getQuantity() {
        return quantity;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    public Product setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public Product setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Product setCategory(Category category) {
        this.category = category;
        return this;
    }

    public Product setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
        return this;
    }

    public Product setImage(String image) {
        this.image = image;
        return this;
    }

    public Product setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public Product setRatings(List<Rating> ratings) {
        this.ratings = ratings;
        return this;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", availabilityStatus=" + availabilityStatus +
                ", image='" + image + '\'' +
                ", quantity=" + quantity +
                ", ratings=" + ratings +
                '}';
    }
}

// Setters with method chaining
