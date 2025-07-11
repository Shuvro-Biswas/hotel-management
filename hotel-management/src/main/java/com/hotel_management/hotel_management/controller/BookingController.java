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
    public ResponseEntity<?> bookRoom(
            @RequestParam Long userId,
            @RequestParam Long roomId) {

        try {
            Booking booking = bookingService.createBooking(userId, roomId);
            return ResponseEntity.ok(booking);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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
