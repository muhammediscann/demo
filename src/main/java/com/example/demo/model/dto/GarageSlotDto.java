package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GarageSlotDto {
    private Long garageSlotId;
    private Integer slotNumber;
    private Boolean status;
    private TicketDto ticket;
}
