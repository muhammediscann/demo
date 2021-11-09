package com.example.demo.service;

import com.example.demo.model.dto.GarageSlotDto;
import com.example.demo.model.response.GarageSlotStatusResponse;
import com.example.demo.model.response.OperationResult;
import com.example.demo.model.dto.TicketDto;

import java.util.List;

public interface GarageSlotService {
    OperationResult saveInitData();

    List<GarageSlotDto> findEmptySlots();

    List<GarageSlotDto> saveGarageSlot(Integer carSlot,
                                       Integer startNumber,
                                       List<GarageSlotDto> garageSlotDtoList,
                                       TicketDto ticketDto);

    OperationResult removeGarageSlots(Long ticketId);


    List<GarageSlotStatusResponse> status();
}
