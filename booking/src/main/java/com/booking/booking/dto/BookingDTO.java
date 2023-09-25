package com.booking.booking.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BookingDTO {
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Concert ID is required")
    private Long concertId;

    public String getUsername() {
        return "";
    }
}

