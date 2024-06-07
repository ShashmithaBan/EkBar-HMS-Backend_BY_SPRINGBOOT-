package com.ekbar.ekbarhms.Controller;

import com.ekbar.ekbarhms.Model.Room;
import com.ekbar.ekbarhms.Request.CreateRoomRequest;
import com.ekbar.ekbarhms.Response.MessageResponse;
import com.ekbar.ekbarhms.Service.RoomService;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/add/{pin}")
    public ResponseEntity<Room> createRoom(
            @RequestBody CreateRoomRequest req,
            @PathVariable Long pin) throws Exception {

        System.out.println("Received request with pin: " + pin);
        System.out.println("Received request body: " + req);

        if (pin.equals(1234L)) {
            Room room = roomService.createRoom(req);
            return new ResponseEntity<>(room, HttpStatus.CREATED);
        } else {

            throw new Exception("Authorized key is invalid");
        }
    }
    @GetMapping("")
    public ResponseEntity<List<Room>> getAllRooms() throws Exception {
        List<Room> room = roomService.getAllRooms();
        return new  ResponseEntity<>(room,HttpStatus.OK);
    }

    @PutMapping("/{id}/updatebooking")
    public ResponseEntity<Room> updateBooking(
            @PathVariable Long id
    )throws Exception{
        Room room = roomService.updateIsBooked(id);
        return new  ResponseEntity<>(room,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Optional<Room> getRoomById(
                @PathVariable Long id
        )throws Exception{
        Optional<Room> room = roomService.getRoomById(id);
        return room;
        }

    @GetMapping("/type")
    public ResponseEntity<List<Room>> getRoomsByRoomType(
    @RequestParam String type
    ) throws Exception {
        List<Room> room = roomService.getRoomsByType(type);
        return new  ResponseEntity<>(room,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteRoom(
            @PathVariable Long id
    ) throws Exception {
        roomService.deleteRoomById(id);
        MessageResponse message = new MessageResponse();
        message.setMessage("Deleted successfully");
        return new  ResponseEntity<>(message,HttpStatus.OK);
    }
}


