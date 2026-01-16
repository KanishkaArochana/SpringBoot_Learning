package com.databaseConfig.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity // Marks this class as a JPA entity (maps to a database table)
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all fields
@Getter // Generates getters for all fields
@Setter // Generates setters for all fields
@Table(name = "users") // Optional: explicitly sets the database table name
public class User {

    // --------- Validation --------------

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment the ID
    private Integer userId;

    @Column(nullable = false, length = 100) // Maps to a DB column, cannot be null, max length 100
    @NotBlank(message = "Username is required!") // Validation: cannot be blank
    private String username;

    @Column(nullable = false, unique = true, length = 150) // Unique column for emails
    @Email(message = "Invalid email format!") // Validation: must be a valid email
    @NotBlank(message = "Email is required!") // Validation: cannot be blank
    private String email;

    @Column(nullable = false) // Maps password column, cannot be null
    @NotBlank(message = "Password is required!") // Validation: cannot be blank
    @Size(min = 6, message = "Password must be at least 6 characters long!") // Validation: min length
    private String password;

    @Column(nullable = false) // Cannot be null
    @NotBlank(message = "Full name is required!") // Validation: cannot be blank
    private String fullName;

    // Store multiple roles for a user (e.g., ROLE_USER, ROLE_ADMIN)
    @ElementCollection(fetch = FetchType.EAGER) // Store as a separate table in DB
    @CollectionTable(
            name = "user_roles", // Name of the table storing roles
            joinColumns = @JoinColumn(name = "user_id") // Link back to user
    )
    @Column(name = "role") // Column name in the user_roles table
    private Set<String> roles;

    @Column(nullable = false)
    @NotNull(message = "Account status is required!") // Boolean must be present
    private Boolean enabled; // true = active, false = deactivated
}
