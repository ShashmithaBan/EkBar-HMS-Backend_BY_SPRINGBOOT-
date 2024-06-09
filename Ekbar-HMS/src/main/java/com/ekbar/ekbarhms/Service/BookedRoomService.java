package com.ekbar.ekbarhms.Service;

import com.ekbar.ekbarhms.Model.BookedRoom;
import com.ekbar.ekbarhms.Request.CreateNewBookingRequest;

import java.util.List;

public interface BookedRoomService {

    public BookedRoom createBooking(CreateNewBookingRequest req);

    public BookedRoom getBookedRoomByBookingConfirmationCode(String code);

    public List<BookedRoom> getAllBookedRoom();

}
