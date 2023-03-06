package de.hsaugsburg.oosd.artx.services;

import de.hsaugsburg.oosd.artx.repositories.ProductRepository;
import de.hsaugsburg.oosd.artx.models.Category;
import de.hsaugsburg.oosd.artx.models.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final String PRICE = "price";

    private static final String YEAR = "year";

    private static final String TITLE = "title";

    private static final String DESCENDING = "desc";

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findProductById(Long productId) {
        return productRepository.findProductById(productId);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findProductsByCategory(Category category) {
        return productRepository.findProductsByCategory(category);
    }

    public List<Product> findProductsBySearchQuery(String searchQuery) {
        return productRepository.findProductsBySearchQuery(searchQuery);
    }

    public List<Product> findProductsByPublicationYear(String publicationYear) {
        // Get the prefix of the period in order to filter the products
        String prefix = publicationYear.substring(0, 2);
        return productRepository.findProductsByPublicationYearStartingWith(prefix);
    }

    public List<Product> sortProducts(List<Product> products, String item, String direction) {
        List<Product> productsSorted;

        switch (item) {
            case PRICE -> {
                productsSorted = products.stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
            }
            case YEAR -> {
                productsSorted = products.stream().sorted(Comparator.comparing(Product::getPublicationYear)).collect(Collectors.toList());
            }
            case TITLE -> {
                productsSorted = products.stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
            }
            default -> productsSorted = findAllProducts();
        }

        if (direction.equals(DESCENDING)) Collections.reverse(productsSorted);

        return productsSorted;
    }

    public BigDecimal getSumOfProductPrices(List<Product> products) {
        BigDecimal sum = new BigDecimal(0);

        for (Product product : products) {
            sum = sum.add(product.getPrice());
        }

        return sum;
    }
}