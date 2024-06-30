package com.ekbar.ekbarhms.Controller;


import com.ekbar.ekbarhms.Model.BookedRoom;
import com.ekbar.ekbarhms.Request.CreateNewBookingRequest;
import com.ekbar.ekbarhms.Response.CreateBookingResponse;
import com.ekbar.ekbarhms.Response.MessageResponse;
import com.ekbar.ekbarhms.Service.BookedRoomService;
import com.ekbar.ekbarhms.Service.BookedRoomServiceImp;
import com.ekbar.ekbarhms.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class BookedRoomController {

    @Autowired
    private BookedRoomService bookedRoomService;

    @PostMapping("")
    public ResponseEntity<CreateBookingResponse> createABooking(@RequestBody CreateNewBookingRequest req) {
        BookedRoom booking = bookedRoomService.createOrUpdateBooking(req);
        String confirmationCode = booking.getBookingConfirmationCode(); // Fetching confirmation code

        // Prepare response
        CreateBookingResponse response = new CreateBookingResponse();
        response.setBookingConfirmationCode(confirmationCode);
        response.setBookedRoom(booking);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/getall")
    public ResponseEntity<List<BookedRoom>> getAllBookings(){
        List<BookedRoom> bookings = bookedRoomService.getAllBookedRoom();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/user/get")
    public ResponseEntity<Map<String, Object>> getABookinByBookingConfirmationCode(@RequestParam String code) {
        BookedRoom booking = bookedRoomService.getBookedRoomByBookingConfirmationCode(code);
        Map<String, Object> response = new HashMap<>();
        response.put("bookingId", booking.getBookingId());
        response.put("checkInDate", booking.getCheckInDate());
        response.put("checkOutDate", booking.getCheckOutDate());
        response.put("guestFullName", booking.getGuestFullName());
        response.put("guestEmail", booking.getGuestEmail());
        response.put("totalNumOfGuest", booking.getTotalNumOfGuest());
        response.put("bookingConfirmationCode", booking.getBookingConfirmationCode());
        response.put("numOfAdults", booking.getNumOfAdults());
        response.put("numOfChildren", booking.getNumOfChildren());
        response.put("roomId", booking.getRoom().getId()); // Add the room ID to the response
        return new ResponseEntity<>(response, HttpStatus.OK);
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
