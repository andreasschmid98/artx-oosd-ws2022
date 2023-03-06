package de.hsaugsburg.oosd.artx.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private CategoryType categoryType;

    @NotNull
    private String name;

    public Category(CategoryType categoryType, String name) {
        this.categoryType = categoryType;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public enum CategoryType {
        MALEREI, GRAFIK, PLASTIK, FLUEGELALTAR, CGI
    }
}
