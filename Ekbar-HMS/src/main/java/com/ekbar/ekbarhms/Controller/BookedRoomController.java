package com.ekbar.ekbarhms.Controller;


import com.ekbar.ekbarhms.Model.BookedRoom;
import com.ekbar.ekbarhms.Request.CreateNewBookingRequest;
import com.ekbar.ekbarhms.Response.MessageResponse;
import com.ekbar.ekbarhms.Service.BookedRoomService;
import com.ekbar.ekbarhms.Service.BookedRoomServiceImp;
import com.ekbar.ekbarhms.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookedRoomController {

    @Autowired
    private BookedRoomService bookedRoomService;

    @PostMapping("")
    public ResponseEntity<BookedRoom> createABooking(
            @RequestBody CreateNewBookingRequest req
            ){
        BookedRoom booking = bookedRoomService.createOrUpdateBooking(req);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }
    @GetMapping("/getall")
    public ResponseEntity<List<BookedRoom>> getAllBookings(){
        List<BookedRoom> bookings = bookedRoomService.getAllBookedRoom();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/user/get")
    public ResponseEntity<BookedRoom> getABookinByBookingConfirmationCode(
            @RequestParam  String code
    ){
        BookedRoom booking = bookedRoomService.getBookedRoomByBookingConfirmationCode(code);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<BookedRoom> updateABooking(
            @RequestBody CreateNewBookingRequest req,
            @RequestParam String code
    ){
        BookedRoom booking = bookedRoomService.updateBooking(req, code);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<MessageResponse> deleteBooking(
            @RequestParam  String code
    ){
       bookedRoomService.deleteBooking(code);
        MessageResponse message = new MessageResponse();
        message.setMessage("Deleted successfully");
        return new  ResponseEntity<>(message,HttpStatus.OK);
    }
}
