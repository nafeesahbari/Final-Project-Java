package com.booking.booking.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data // Lombok automatically generates getters & setters
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;
}
