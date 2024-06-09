package com.ekbar.ekbarhms.Repo;

import com.ekbar.ekbarhms.Model.BookedRoom;
import com.ekbar.ekbarhms.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookedRoomRepo extends JpaRepository<BookedRoom,Long> {

    @Query("SELECT b FROM BookedRoom b WHERE b.room = :room AND b.checkInDate < :checkOutDate AND b.checkOutDate > :checkInDate")
    List<BookedRoom> findByRoomAndCheckInDateBeforeAndCheckOutDateAfter(
            @Param("room") Room room,
            @Param("checkOutDate") LocalDate checkOutDate,
            @Param("checkInDate") LocalDate checkInDate
    );
}
