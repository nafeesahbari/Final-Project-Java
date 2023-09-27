package com.booking.booking.controller;

import com.booking.booking.dto.BookingDTO;
import com.booking.booking.model.Booking;
import com.booking.booking.model.Concert;
import com.booking.booking.model.User;
import com.booking.booking.repository.BookingRepository;
import com.booking.booking.repository.ConcertRepository;
import com.booking.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ConcertRepository concertRepository;

    @Autowired
    public BookingController(BookingRepository bookingRepository, UserRepository userRepository, ConcertRepository concertRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.concertRepository = concertRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createBooking(@Valid @RequestBody BookingDTO bookingDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Handle validation errors
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        // Check if the user and concert exist
        User user = userRepository.findByUsername(bookingDTO.getUsername());
        Concert concert = concertRepository.findById(bookingDTO.getConcertId()).orElse(null);

        if (user == null || concert == null) {
            return new ResponseEntity<>("User or Concert not found", HttpStatus.NOT_FOUND);
        }

        // Create a new booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setConcert(concert);
        booking.setBookingDate(new Date());

        bookingRepository.save(booking);

        return new ResponseEntity<>("Booking created successfully!", HttpStatus.CREATED);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Booking> allBookings = bookingRepository.findAll();
        List<Booking> userBookings = allBookings.stream()
                .filter(booking -> booking.getUser().equals(user))
                .collect(Collectors.toList());

        return new ResponseEntity<>(userBookings, HttpStatus.OK);
    }

    @GetMapping("/concert/{concertId}")
    public ResponseEntity<List<Booking>> getConcertBookings(@PathVariable Long concertId) {
        Concert concert = concertRepository.findById(concertId).orElse(null);
        if (concert == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Booking> allBookings = bookingRepository.findAll();
        List<Booking> concertBookings = allBookings.stream()
                .filter(booking -> booking.getConcert().equals(concert))
                .collect(Collectors.toList());

        return new ResponseEntity<>(concertBookings, HttpStatus.OK);
    }
}

