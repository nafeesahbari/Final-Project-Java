package com.booking.booking.model;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String concertName;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String venue;
}