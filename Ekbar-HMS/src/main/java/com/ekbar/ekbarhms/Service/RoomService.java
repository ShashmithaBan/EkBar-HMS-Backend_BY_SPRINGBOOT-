package com.ekbar.ekbarhms.Service;

import com.ekbar.ekbarhms.Model.Room;
import com.ekbar.ekbarhms.Request.CreateRoomRequest;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    public Room createRoom(CreateRoomRequest req);

    public List<Room> getAllRooms();

    public Room updateIsBooked(Long id);

    public void deleteRoomById(Long id);

    public Optional<Room> getRoomById(Long id);

    public List<Room> getRoomsByType(String type);


}