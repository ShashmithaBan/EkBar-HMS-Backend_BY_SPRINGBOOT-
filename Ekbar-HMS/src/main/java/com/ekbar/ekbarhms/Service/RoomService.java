package com.ekbar.ekbarhms.Service;

import com.ekbar.ekbarhms.Model.Room;
import com.ekbar.ekbarhms.Request.CreateRoomRequest;

import java.util.List;

public interface RoomService {

    public Room createRoom(CreateRoomRequest req);

    public List<Room> getAllRooms();

    public Room updateIsBooked(Long id);

    public void deleteRoomById(Long id) throws Exception;

    public Room getRoomById(Long id) throws Exception;

    public List<Room> getRoomsByType(String type);

    public List<Room> getAllAvailableRooms();

    public  List<Room> getAllAvailableRoomsWithSpecificRoomType( String type);

    public Room updateRoomDetails(CreateRoomRequest req , Long roomId) throws Exception;
}