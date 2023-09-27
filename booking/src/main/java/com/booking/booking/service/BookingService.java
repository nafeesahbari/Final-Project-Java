package com.booking.booking.service;

import com.booking.booking.model.Booking;
import com.booking.booking.model.Concert;
import com.booking.booking.model.User;

import java.util.List;

public interface BookingService {
    Booking createBooking(User user, Concert concert);
    List<Booking> getUserBookings(User user);
    List<Booking> getConcertBookings(Concert concert);
}

