package com.ekbar.ekbarhms.Service;

import com.ekbar.ekbarhms.Model.BookedRoom;
import com.ekbar.ekbarhms.Model.Room;
import com.ekbar.ekbarhms.Repo.BookedRoomRepo;
import com.ekbar.ekbarhms.Repo.RoomRepo;
import com.ekbar.ekbarhms.Request.CreateNewBookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Service
public class BookedRoomServiceImp implements BookedRoomService{

    @Autowired
    public RoomRepo roomRepo;

    @Autowired
    public BookedRoomRepo bookedRoomRepo;

    public BookedRoom createBooking(CreateNewBookingRequest req) {
        if (checkWhetherTheRoomIsAvailable(req.getRoomId(), req.getCheckInDate(), req.getCheckOutDate())) {
            BookedRoom booking = new BookedRoom();
            booking.setCheckInDate(req.getCheckInDate());
            booking.setCheckOutDate(req.getCheckOutDate());
            booking.setBookingConfirmationCode(generateUniqueCode());
            booking.setGuestEmail(req.getGuestEmail());
            booking.setGuestFullName(req.getGuestFullName());
            booking.setNumOfAdults(req.getNumOfAdults());
            booking.setNumOfChildren(req.getNumOfChildren());
            booking.setTotalNumOfGuest(totalNoOfGuests(req.getNumOfAdults(), req.getNumOfChildren()));

            Optional<Room> roomOptional = roomRepo.findById(req.getRoomId());
            if (roomOptional.isPresent()) {
                booking.setRoom(roomOptional.get());
            } else {
                throw new IllegalArgumentException("Invalid room ID: " + req.getRoomId());
            }

            return bookedRoomRepo.save(booking);
        } else {
            throw new IllegalArgumentException("Room with ID " + req.getRoomId() + " is already booked for the selected dates.");
        }
    }

    public boolean checkWhetherTheRoomIsAvailable(Long roomId, LocalDate checkInDate, LocalDate checkOutDate) {
        Optional<Room> roomOptional = roomRepo.findById(roomId);
        if (!roomOptional.isPresent()) {
            throw new IllegalArgumentException("Invalid room ID: " + roomId);
        }

        Room room = roomOptional.get();


        List<BookedRoom> bookings = bookedRoomRepo.findByRoomAndCheckInDateBeforeAndCheckOutDateAfter(
                room, checkOutDate, checkInDate);

        return bookings.isEmpty();
    }


    @Override
    public BookedRoom updateBooking(CreateNewBookingRequest req, String bookingConfirmationCode) {
        return null;
    }

    @Override
    public BookedRoom getBookedRoomByBookingConfirmationCode(String code) {
        return null;
    }

    @Override
    public List<BookedRoom> getAllBookedRoom() {
        return null;
    }

    @Override
    public String generateUniqueCode() {
        return  UUID.randomUUID().toString();
    }

    @Override
    public int totalNoOfGuests(int NumOfAdults, int NumOfChildrens) {
        return NumOfAdults+NumOfChildrens;
    }


}