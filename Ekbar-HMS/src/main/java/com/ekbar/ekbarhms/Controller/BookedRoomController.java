package com.ekbar.ekbarhms.Controller;


import com.ekbar.ekbarhms.Model.BookedRoom;
import com.ekbar.ekbarhms.Request.CreateNewBookingRequest;
import com.ekbar.ekbarhms.Service.BookedRoomService;
import com.ekbar.ekbarhms.Service.BookedRoomServiceImp;
import com.ekbar.ekbarhms.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class BookedRoomController {

    @Autowired
    private BookedRoomService bookedRoomService;

    @PostMapping("")
    public ResponseEntity<BookedRoom> createABooking(
            @RequestBody CreateNewBookingRequest req
            ){
        BookedRoom booking = bookedRoomService.createBooking(req);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }
}
