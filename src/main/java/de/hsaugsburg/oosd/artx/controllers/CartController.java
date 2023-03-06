package de.hsaugsburg.oosd.artx.controllers;

import de.hsaugsburg.oosd.artx.models.Product;
import de.hsaugsburg.oosd.artx.models.User;
import de.hsaugsburg.oosd.artx.services.EmailService;
import de.hsaugsburg.oosd.artx.services.ProductService;
import de.hsaugsburg.oosd.artx.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController implements Product.ProductBoughtListener {

    private final ProductService productService;

    private final UserService userService;

    private final EmailService emailService;

    private User user;

    public CartController(ProductService productService, UserService userService, EmailService emailService) {
        this.productService = productService;
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/toggle-cart/{productId}")
    public String toggleCartItem(HttpServletRequest request, @PathVariable("productId") Long productId) {

        Product product = productService.findProductById(productId);
        user = userService.getCurrentUser();

        if (!user.cartContainsItem(product)) {
            user.addCartItem(product);
        } else {
            user.removeCartItem(product);
        }

        userService.save(user);

        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/cart")
    public String cart(Model model) {

        user = userService.getCurrentUser();

        model.addAttribute("user", user);
        model.addAttribute("sum", productService.getSumOfProductPrices(user.getCartItems()));

        return "home/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {

        user = userService.getCurrentUser();

        // Redirect user to his cart if he trys to manually open the checkout page
        if (user.getCartItems() == null || user.getCartItems().size() == 0) {
            return "redirect:/cart";
        }

        model.addAttribute("user", user);
        model.addAttribute("sum", productService.getSumOfProductPrices(user.getCartItems()));
        model.addAttribute("cartItems", user.getCartItems());

        // Try to send the confirmation mail
        try {
            emailService.sendEmail(user.getEmail(), user.getCartItems());

            // If sending was successfully, toggle the status of every bought item
            for (Product item : user.getCartItems()) {
                item.toggleStatus();
                productService.save(item);
                onProductBought(user, item);
            }

            // Clear the user cart and save the updated user entity
            user.setCartItems(new ArrayList<>());
            userService.save(user);

        } catch (Exception e) {

            // Print Exception information to get details about it
            e.printStackTrace();

            // If sending failed (e.g. internet connection lost), display an error message
            return "home/order_failed";
        }

        return "home/order_confirmation";
    }

    @Override
    public void onProductBought(User currentUser, Product product) {
        List<User> allUsers = userService.findAllUsers();
        allUsers.remove(currentUser);

        // Remove the bought product from all carts if the cart contains this product
        for (User user : allUsers) {
            if (user.cartContainsItem(product)) {
                user.removeCartItem(product);
                userService.save(user);
            }
        }
    }
}

