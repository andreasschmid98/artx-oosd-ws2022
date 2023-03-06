package de.hsaugsburg.oosd.artx.repositories;

import de.hsaugsburg.oosd.artx.models.Category;
import de.hsaugsburg.oosd.artx.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(Long productId);

    List<Product> findDistinctByArtistContainingIgnoreCaseOrNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String artist,
                                                                                                                      String name,
                                                                                                                      String description);

    List<Product> findProductsByPublicationYearStartingWith(String prefix);

    List<Product> findProductsByCategory(Category category);

    @Query("SELECT p FROM Product p WHERE TO_CHAR(p.publicationYear) LIKE %:publicationYear%")
    List<Product> findProductsByPublicationYearContaining(@Param("publicationYear") String date);

    @Query("SELECT p FROM Product p WHERE TO_CHAR(p.price) LIKE %:price%")
    List<Product> findProductsByPriceContaining(@Param("price") String price);

    /**
     * This implements the search functionality by combining the search results of several findBy-methods.
     */
    default List<Product> findProductsBySearchQuery(String searchQuery) {
        if (searchQuery != null) {
            List<Product> resultSetArtistNameDescr = findDistinctByArtistContainingIgnoreCaseOrNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchQuery, searchQuery, searchQuery);
            List<Product> resultSetPublicationYear = findProductsByPublicationYearContaining(searchQuery);
            List<Product> resultSetPrice = findProductsByPriceContaining(searchQuery);

            Set<Product> resultSet = new HashSet<>(resultSetArtistNameDescr);
            resultSet.addAll(resultSetPublicationYear);
            resultSet.addAll(resultSetPrice);
            List<Product> products = new ArrayList<>(resultSet);

            return products;
        }
        return findAll();
    }
}

