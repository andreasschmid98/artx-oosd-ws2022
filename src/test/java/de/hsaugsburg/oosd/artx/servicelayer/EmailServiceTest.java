package de.hsaugsburg.oosd.artx.servicelayer;

import de.hsaugsburg.oosd.artx.models.Category;
import de.hsaugsburg.oosd.artx.models.Product;
import de.hsaugsburg.oosd.artx.services.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    JavaMailSender mailSender;

    private EmailService emailService;

    List<Product> products = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Category category = new Category(Category.CategoryType.MALEREI, "Malerei");
        Product product = new Product(
                "test",
                "test",
                new BigDecimal(1),
                "2000",
                category,
                "test",
                "test"
        );
        products.add(product);

        emailService = new EmailService(mailSender);
    }

    @Test
    void testSendThrowsNoException() {
        assertDoesNotThrow(() -> emailService.sendEmail("test@test.com", products));
    }
}
