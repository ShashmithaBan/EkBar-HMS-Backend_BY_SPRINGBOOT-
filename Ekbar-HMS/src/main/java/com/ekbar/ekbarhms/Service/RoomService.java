package com.ekbar.ekbarhms.Service;

import com.ekbar.ekbarhms.Model.Room;
import com.ekbar.ekbarhms.Request.CreateRoomRequest;

import java.util.List;

public interface RoomService {

    public Room createRoom(CreateRoomRequest req);

    public List<Room> getAllRooms();

    public void updateIsBooked();

    public void deleteRoom();
}