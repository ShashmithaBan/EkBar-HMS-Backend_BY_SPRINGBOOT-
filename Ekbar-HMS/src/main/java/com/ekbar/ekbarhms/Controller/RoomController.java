package com.ekbar.ekbarhms.Controller;

import com.ekbar.ekbarhms.Model.Room;
import com.ekbar.ekbarhms.Request.CreateRoomRequest;
import com.ekbar.ekbarhms.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}

