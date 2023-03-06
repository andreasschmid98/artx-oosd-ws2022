package de.hsaugsburg.oosd.artx.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Status status;

    @NotNull
    private String artist;

    @NotNull
    private BigDecimal price;

    @NotNull
    String publicationYear;

    @ManyToOne
    private Category category;

    @NotNull
    private String description;

    @NotNull
    private String imageUrl;

    /**
     * Constructor used for creating test products
     */
    public Product(String name, String artist, BigDecimal price, String publicationYear, Category category, String description, String imageUrl) {
        this.name = name;
        this.artist = artist;
        this.price = price;
        this.publicationYear = publicationYear;
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;
        this.status = Status.AVAILABLE;
    }

    /**
     * DO NOT DELETE.
     * This is used from Thymeleaf. However, IntelliJ marks it as unused.
     */
    public boolean isAvailable() {
        return status == Status.AVAILABLE;
    }

    public void toggleStatus() {
        if (status == Status.AVAILABLE) {
            status = Status.SOLD;
        } else {
            status = Status.AVAILABLE;
        }
    }

    public enum Status {
        AVAILABLE, SOLD
    }

    public interface ProductBoughtListener {
        void onProductBought(User currentUser, Product product);
    }
}


