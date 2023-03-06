package de.hsaugsburg.oosd.artx.controllers;

import de.hsaugsburg.oosd.artx.models.User;
import de.hsaugsburg.oosd.artx.services.user.UserDto;
import de.hsaugsburg.oosd.artx.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "authentication/login";
    }

    @GetMapping("register")
    public String showRegistrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "authentication/register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(user.getEmail());

        if (existingUser != null) {
            result.rejectValue("email", null, "Es gibt bereits einen Account mit dieser Email!");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "authentication/register";
        }

        userService.save(user);
        return "redirect:/register?success";
    }
}
