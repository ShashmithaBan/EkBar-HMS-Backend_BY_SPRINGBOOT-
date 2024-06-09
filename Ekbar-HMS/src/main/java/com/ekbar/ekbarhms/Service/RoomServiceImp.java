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
    public void deleteRoomById(Long id) throws Exception {
       Room room = getRoomById(id);
    roomRepo.delete(room);
    }


    @Override
    public Room getRoomById(Long id) throws Exception {
        Optional<Room> opt = roomRepo.findById(id);
        if(opt.isEmpty()){
            throw new Exception("room not found with this id"+id);
        }

        return opt.get();


    }

    @Override
    public List<Room> getRoomsByType(String type) {

        return roomRepo.findRoomByRoomType(type);
    }

    @Override
    public List<Room> getAllAvailableRooms() {
        return roomRepo.findByIsBookedFalse();
    }

    @Override
    public List<Room> getAllAvailableRoomsWithSpecificRoomType(String type) {
        return roomRepo.findAvailableRoomsByType(type);
    }

    @Override
    public Room updateRoomDetails(CreateRoomRequest req , Long roomId) throws Exception {
        Room room =getRoomById(roomId);
        if(room.getRoomPrice()!= null){
            room.setRoomPrice(req.getRoomPrice());
        }
        if(room.getRoomType()!=null){
            room.setRoomType(req.getRoomType());
        }
        if(room.getImages()!=null){
            room.setImages(req.getImages());
        }
        return roomRepo.save(room);

    }


}

