package com.hotel_management.hotel_management.controller;


import com.hotel_management.hotel_management.entity.Booking;
import com.hotel_management.hotel_management.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired private BookingService bookingService;

    @PostMapping("/student")
    public ResponseEntity<?> createBooking(@RequestParam Long userId, @RequestParam Long roomId) {
        Booking booking = bookingService.createBooking(userId, roomId);
        if (booking == null) return ResponseEntity.badRequest().body("Booking failed.");
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingsByStudent(id));
    }

    @PutMapping("/admin/status")
    public ResponseEntity<?> updateStatus(@RequestParam Long bookingId, @RequestParam String status) {
        Booking booking = bookingService.updateStatus(bookingId, status);
        if (booking == null) return ResponseEntity.badRequest().body("Invalid Booking ID");
        return ResponseEntity.ok(booking);
    }
}
