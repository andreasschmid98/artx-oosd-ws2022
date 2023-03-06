package de.hsaugsburg.oosd.artx.services.user;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * This is used for user registration.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotEmpty(message = "Feld Vorname darf nicht leer sein")
    private String firstName;

    @NotEmpty(message = "Feld Nachname darf nicht leer sein")
    private String lastName;

    @NotEmpty(message = "Feld E-Mail Adresse darf nicht leer sein")
    @Email
    private String email;

    @NotEmpty(message = "Feld Passwort darf nicht leer sein")
    private String password;
}
