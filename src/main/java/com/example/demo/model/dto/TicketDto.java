package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDto {
    private Long ticketId;
    private String plate;
    private String carType;
    private String colour;
    private Boolean status;
}
