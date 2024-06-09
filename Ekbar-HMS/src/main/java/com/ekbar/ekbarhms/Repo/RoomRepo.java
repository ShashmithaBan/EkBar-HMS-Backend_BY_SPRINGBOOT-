package com.ekbar.ekbarhms.Repo;

import com.ekbar.ekbarhms.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RoomRepo extends JpaRepository<Room,Long> {

    @Query("SELECT r FROM Room r WHERE r.roomType LIKE %:type%")
    List<Room> findRoomByRoomType(@Param("type") String type);


    List<Room> findByIsBookedFalse();

    @Query("SELECT r FROM Room r WHERE r.roomType LIKE %:type% AND r.isBooked = false")
    List<Room> findAvailableRoomsByType(@Param("type") String type);


}
