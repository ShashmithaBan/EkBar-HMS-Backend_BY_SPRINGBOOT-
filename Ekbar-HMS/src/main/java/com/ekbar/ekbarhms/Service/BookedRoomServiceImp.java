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
    public RoomService roomService;

    @Autowired
    public BookedRoomRepo bookedRoomRepo;

    public BookedRoom createOrUpdateBooking(CreateNewBookingRequest req) {
        // Check if the room is available for the given time range
        if (checkWhetherTheRoomIsAvailable(req.getRoomId(), req.getCheckInDate(), req.getCheckOutDate())) {
            Optional<BookedRoom> existingBookingOptional = bookedRoomRepo.findByRoomIdAndDateRange(
                    req.getRoomId(),
                    req.getCheckInDate(),
                    req.getCheckOutDate()
            );

            BookedRoom booking;
            if (existingBookingOptional.isPresent()) {
                // Update existing booking
                booking = existingBookingOptional.get();
            } else {
                // Create a new booking
                booking = new BookedRoom();
                booking.setBookingConfirmationCode(generateUniqueCode());
                roomService.updateIsBooked(req.getRoomId());
            }


            booking.setCheckInDate(req.getCheckInDate());
            booking.setCheckOutDate(req.getCheckOutDate());
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
    public void deleteBooking(String bookingConfirmationCode) {
        BookedRoom booking = getBookedRoomByBookingConfirmationCode(bookingConfirmationCode);
        bookedRoomRepo.delete(booking);
    }


    @Override
    public BookedRoom updateBooking(CreateNewBookingRequest req, String bookingConfirmationCode) {
        BookedRoom booking = getBookedRoomByBookingConfirmationCode(bookingConfirmationCode);
       if(booking.getGuestEmail()!=null){
           booking.setGuestEmail(req.getGuestEmail());
       }
       if(booking.getCheckOutDate()!=null){
           booking.setCheckOutDate(req.getCheckOutDate());
       }
       if(booking.getCheckOutDate()!=null){
           booking.setCheckOutDate(req.getCheckOutDate());
       }
       if(booking.getGuestFullName()!=null){
           booking.setGuestFullName(req.getGuestFullName());
       }
       booking.setNumOfChildren(req.getNumOfChildren());
       booking.setNumOfAdults(req.getNumOfAdults());
       booking.setTotalNumOfGuest(totalNoOfGuests(req.getNumOfAdults(),req.getNumOfChildren()));

        return bookedRoomRepo.save(booking);
    }

    @Override
    public BookedRoom getBookedRoomByBookingConfirmationCode(String code) {
        return bookedRoomRepo.findByBookingConfirmationCode(code);
    }

    @Override
    public List<BookedRoom> getAllBookedRoom() {
        return bookedRoomRepo.findAll();
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
