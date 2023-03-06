package de.hsaugsburg.oosd.artx;

import de.hsaugsburg.oosd.artx.controllers.AuthenticationController;
import de.hsaugsburg.oosd.artx.controllers.CartController;
import de.hsaugsburg.oosd.artx.controllers.HomeController;
import de.hsaugsburg.oosd.artx.controllers.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ControllerLayerTest {

    @Autowired
    private HomeController homeController;

    @Autowired
    private ProductController productController;

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private CartController cartController;

    @Test
    void controllerLoads() {
        assertThat(homeController).isNotNull();
        assertThat(productController).isNotNull();
        assertThat(authenticationController).isNotNull();
        assertThat(cartController).isNotNull();
    }
}
