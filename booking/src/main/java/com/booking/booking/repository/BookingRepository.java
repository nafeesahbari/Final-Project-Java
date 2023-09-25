package com.booking.booking.repository;

import com.booking.booking.model.Booking;
import com.booking.booking.model.Concert;
import com.booking.booking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByUser(User user);

    List<Booking> findAllByConcert(Concert concert);
}