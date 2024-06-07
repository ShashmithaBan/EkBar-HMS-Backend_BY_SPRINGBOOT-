package com.ekbar.ekbarhms.Service;

import com.ekbar.ekbarhms.Model.Room;
import com.ekbar.ekbarhms.Repo.RoomRepo;
import com.ekbar.ekbarhms.Request.CreateRoomRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        return roomRepo.findAll();
    }

    @Override
    public Room updateIsBooked(Long id)  {
        Room room = roomRepo.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        room.setBooked(!room.isBooked());
        return roomRepo.save(room);
    }


@Override
    public void deleteRoomById(Long id) {
        Optional<Room> roomOptional = getRoomById(id);
    roomRepo.delete(roomOptional.get());
    }


    @Override
    public Optional<Room> getRoomById(Long id) {
        Optional<Room> room = roomRepo.findById(id);
        if (room.isPresent()) {
            return room;

        } else {

            throw new IllegalArgumentException("Room not found with id: " + id);
        }

    }

    @Override
    public List<Room> getRoomsByType(String type) {

        return roomRepo.findRoomByRoomType(type);
    }



}

