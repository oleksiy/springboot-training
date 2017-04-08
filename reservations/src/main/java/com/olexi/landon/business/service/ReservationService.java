package com.olexi.landon.business.service;

import com.olexi.landon.business.domain.RoomReservation;
import com.olexi.landon.data.entity.Guest;
import com.olexi.landon.data.entity.Reservation;
import com.olexi.landon.data.entity.Room;
import com.olexi.landon.data.repository.GuestRepository;
import com.olexi.landon.data.repository.ReservationRepository;
import com.olexi.landon.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by oblavat on 4/8/17.
 */
@Service
public class ReservationService {
    private RoomRepository roomRepository;
    private GuestRepository guestRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date){
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getNumber());
            roomReservationMap.put(room.getId(), roomReservation);
        });
        Iterable<Reservation> reservations = this.reservationRepository.findByDate(new java.sql.Date(date.getTime()));
        if(null != reservations) {
            reservations.forEach(reservation -> {
                Guest guest = this.guestRepository.findOne(reservation.getGuestId());
                if(null != guest) {
                    RoomReservation roomReservation = roomReservationMap.get(reservation.getId());
                    roomReservation.setDate(date);
                    roomReservation.setFirstName(guest.getFirstName());
                    roomReservation.setLastName(guest.getLastName());
                    roomReservation.setGuestId(guest.getId());
                }
            });
        }
        List<RoomReservation> roomReservations = new ArrayList<>();
        for(Long roomId:roomReservationMap.keySet()){
            roomReservations.add(roomReservationMap.get(roomId));
        }
        return roomReservations;
    }
}
