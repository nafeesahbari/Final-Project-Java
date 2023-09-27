package com.booking.booking.service;

import com.booking.booking.model.Booking;
import com.booking.booking.model.Concert;
import com.booking.booking.model.User;
import com.booking.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking createBooking(User user, Concert concert) {
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setConcert(concert);
        // Set the booking date to the current date and time
        booking.setBookingDate(new Date());
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getUserBookings(User user) {
        List<Booking> allBookings = bookingRepository.findAll();
        return allBookings.stream()
                .filter(booking -> booking.getUser().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> getConcertBookings(Concert concert) {
        List<Booking> allBookings = bookingRepository.findAll();
        return allBookings.stream()
                .filter(booking -> booking.getConcert().equals(concert))
                .collect(Collectors.toList());
    }
}
