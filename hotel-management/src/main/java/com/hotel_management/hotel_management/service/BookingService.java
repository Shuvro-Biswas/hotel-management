package com.hotel_management.hotel_management.service;

import com.hotel_management.hotel_management.entity.Booking;
import com.hotel_management.hotel_management.entity.Room;
import com.hotel_management.hotel_management.entity.User;
import com.hotel_management.hotel_management.repository.BookingRepository;
import com.hotel_management.hotel_management.repository.RoomRepository;
import com.hotel_management.hotel_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired private BookingRepository bookingRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private RoomRepository roomRepository;

    public Booking createBooking(Long userId, Long roomId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User with userId=" + userId + " not found.");
        }

        Optional<Room> roomOpt = roomRepository.findById(roomId);
        if (roomOpt.isEmpty()) {
            throw new IllegalArgumentException("Room with roomId=" + roomId + " not found.");
        }

        Room room = roomOpt.get();
        if (!room.isAvailable()) {
            throw new IllegalStateException("Room with roomId=" + roomId + " is not available.");
        }

        Booking booking = Booking.builder()
                .student(userOpt.get())
                .room(room)
                .bookingDate(LocalDate.now())
                .status("PENDING")
                .build();

        room.setAvailable(false);
        roomRepository.save(room);
        return bookingRepository.save(booking);
    }



    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByStudent(Long studentId) {
        return bookingRepository.findByStudentId(studentId);
    }

    public Booking updateStatus(Long bookingId, String status) {
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            booking.setStatus(status);
            return bookingRepository.save(booking);
        }
        return null;
    }
}
