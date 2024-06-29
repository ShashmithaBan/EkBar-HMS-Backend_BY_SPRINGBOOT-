package com.ekbar.ekbarhms.Repo;

import com.ekbar.ekbarhms.Model.BookedRoom;
import com.ekbar.ekbarhms.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookedRoomRepo extends JpaRepository<BookedRoom,Long> {

    @Query("SELECT b FROM BookedRoom b WHERE b.room = :room AND b.checkInDate < :checkOutDate AND b.checkOutDate > :checkInDate")
    List<BookedRoom> findByRoomAndCheckInDateBeforeAndCheckOutDateAfter(
            @Param("room") Room room,
            @Param("checkOutDate") LocalDate checkOutDate,
            @Param("checkInDate") LocalDate checkInDate
    );
    BookedRoom findByBookingConfirmationCode(String bookingConfirmationCode);

    @Query("SELECT br FROM BookedRoom br WHERE br.room.id = :roomId AND " +
            "((:checkInDate BETWEEN br.checkInDate AND br.checkOutDate) OR " +
            "(:checkOutDate BETWEEN br.checkInDate AND br.checkOutDate) OR " +
            "(br.checkInDate BETWEEN :checkInDate AND :checkOutDate) OR " +
            "(br.checkOutDate BETWEEN :checkInDate AND :checkOutDate))")
    Optional<BookedRoom> findByRoomIdAndDateRange(
            @Param("roomId") Long roomId,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate
    );
}
