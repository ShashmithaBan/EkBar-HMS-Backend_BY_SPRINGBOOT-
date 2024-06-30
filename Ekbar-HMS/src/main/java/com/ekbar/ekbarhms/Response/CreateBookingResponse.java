package com.ekbar.ekbarhms.Response;

import com.ekbar.ekbarhms.Model.BookedRoom;
import lombok.Data;

@Data
public class CreateBookingResponse {
    private String bookingConfirmationCode;
    private BookedRoom bookedRoom;
}
