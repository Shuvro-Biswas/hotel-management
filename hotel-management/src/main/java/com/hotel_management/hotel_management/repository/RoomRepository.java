package com.hotel_management.hotel_management.repository;

import com.hotel_management.hotel_management.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
