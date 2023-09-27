package com.booking.booking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;

// Lombok constructor
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "concert_id", nullable = false)
    private Concert concert;

    @Column(name = "booking_date", nullable = false)
    private Date bookingDate;
}
