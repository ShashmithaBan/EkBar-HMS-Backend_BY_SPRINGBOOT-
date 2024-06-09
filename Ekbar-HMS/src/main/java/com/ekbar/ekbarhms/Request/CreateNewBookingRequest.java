package com.ekbar.ekbarhms.Request;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateNewBookingRequest {

    private  Long roomId;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;


    private String guestFullName;


    private String guestEmail;


    private int NumOfAdults;


    private int NumOfChildren;
}
