package com.ekbar.ekbarhms.Service;

import com.ekbar.ekbarhms.Model.Room;
import com.ekbar.ekbarhms.Repo.RoomRepo;
import com.ekbar.ekbarhms.Request.CreateRoomRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImp implements RoomService {

    @Autowired
    public RoomRepo roomRepo;

    @Override
    public Room createRoom(CreateRoomRequest req) {
        Room room = new Room();
        room.setRoomPrice(req.getRoomPrice());
        room.setRoomType(req.getRoomType());
        room.setImages(req.getImages());
        return roomRepo.save(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return null;
    }

    @Override
    public void updateIsBooked() {

    }

    @Override
    public void deleteRoom() {

    }
}

