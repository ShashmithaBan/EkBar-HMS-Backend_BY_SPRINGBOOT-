package com.ekbar.ekbarhms.Service;

import com.ekbar.ekbarhms.Model.BookedRoom;
import com.ekbar.ekbarhms.Request.CreateNewBookingRequest;

import java.time.LocalDate;
import java.util.List;

public interface BookedRoomService {

    public BookedRoom createOrUpdateBooking(CreateNewBookingRequest req);

    public BookedRoom updateBooking(CreateNewBookingRequest req , String bookingConfirmationCode);

    public BookedRoom getBookedRoomByBookingConfirmationCode(String code);

    public List<BookedRoom> getAllBookedRoom();

    public String generateUniqueCode();

    public int totalNoOfGuests(int NumOfAdults , int NumOfChildrens);


    public boolean checkWhetherTheRoomIsAvailable(Long roomId, LocalDate checkInDate, LocalDate checkOutDate);

    public  void deleteBooking(String bookingConfirmationCode);

}
