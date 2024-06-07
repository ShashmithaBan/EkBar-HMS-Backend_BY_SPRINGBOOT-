package com.ekbar.ekbarhms.Request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateRoomRequest {

    private Long id;
    private String name;
    private String roomType;
    private BigDecimal roomPrice;
    private List<String> images;

}
