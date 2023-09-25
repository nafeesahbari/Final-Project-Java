package com.booking.booking.controller;

import com.booking.booking.model.Concert;
import com.booking.booking.repository.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/concerts")
public class ConcertController {

    private final ConcertRepository concertRepository;

    @Autowired
    public ConcertController(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Concert>> getAllConcerts() {
        List<Concert> concerts = concertRepository.findAll();
        return new ResponseEntity<>(concerts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Concert> getConcertById(@PathVariable Long id) {
        Optional<Concert> concertOptional = concertRepository.findById(id);
        return concertOptional.map(concert -> new ResponseEntity<>(concert, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
