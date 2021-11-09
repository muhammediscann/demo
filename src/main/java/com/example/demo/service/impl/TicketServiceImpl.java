package com.example.demo.service.impl;

import com.example.demo.exception.OperationResultException;
import com.example.demo.model.dto.GarageSlotDto;
import com.example.demo.model.dto.TicketDto;
import com.example.demo.model.entity.TicketEntity;
import com.example.demo.model.enums.CarType;
import com.example.demo.model.enums.OperationResultCode;
import com.example.demo.model.mapper.TicketMapper;
import com.example.demo.model.request.ParkRequest;
import com.example.demo.model.response.OperationResult;
import com.example.demo.model.response.TicketResponse;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.GarageSlotService;
import com.example.demo.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private final GarageSlotService garageSlotService;
    private final TicketRepository ticketRepository;


    @Transactional
    @Override
    public TicketResponse park(ParkRequest parkRequest) {
        Integer startSlot = NumberUtils.INTEGER_ZERO;
        Integer countSlot = NumberUtils.INTEGER_ONE;
        Integer carSLot = CarType.getSlot(parkRequest.getCarType().name());

        List<GarageSlotDto> emptySlots = garageSlotService.findEmptySlots();
        for (int index = NumberUtils.INTEGER_ZERO; index < emptySlots.size(); index++) {
            if (index + NumberUtils.INTEGER_ONE == emptySlots.size()) {
                if (carSLot < emptySlots.size()) {
                    startSlot = emptySlots.get(index - 1).getSlotNumber();
                } else {
                    startSlot = emptySlots.get(index).getSlotNumber();
                }
                break;
            }

            Integer currentSlotNumberPlusOne = emptySlots.get(index).getSlotNumber() + NumberUtils.INTEGER_ONE;
            Integer nextSlotNumber = emptySlots.get(index + NumberUtils.INTEGER_ONE).getSlotNumber();
            if (carSLot.equals(NumberUtils.INTEGER_ONE)) {
                countSlot = 1;
                startSlot = emptySlots.get(index).getSlotNumber();
                break;
            } else if (currentSlotNumberPlusOne.equals(nextSlotNumber)) {
                if (startSlot.equals(NumberUtils.INTEGER_ZERO)) {
                    startSlot = emptySlots.get(index).getSlotNumber();
                }

                if (countSlot.equals(carSLot)) {
                    break;
                }

                countSlot++;
            } else {
                countSlot = NumberUtils.INTEGER_ONE;
                startSlot = NumberUtils.INTEGER_ZERO;
            }
        }

        if (countSlot.equals(carSLot)) {
            TicketEntity ticketEntity = TicketEntity.builder()
                    .carType(parkRequest.getCarType().name())
                    .colour(parkRequest.getColour())
                    .status(Boolean.TRUE)
                    .plate(parkRequest.getPlate()).build();
            ticketRepository.save(ticketEntity);
            TicketDto ticketDto = TicketMapper.INSTANCE.toTicketDto(ticketEntity);
            garageSlotService.saveGarageSlot(carSLot, startSlot, emptySlots, ticketDto);
            return TicketResponse.builder().ticketId(ticketEntity.getTicketId()).build();
        } else {
            throw OperationResultException.builder()
                    .operationResult(OperationResult.newInstance(OperationResultCode.ERROR, "Garage is full!"))
                    .build();
        }
    }

    @Override
    public OperationResult leave(Long ticketId) {
        TicketEntity ticketEntity = ticketRepository.findById(ticketId)
                .orElseThrow(() -> OperationResultException.builder()
                        .operationResult(OperationResult.newInstance(OperationResultCode.ERROR, "Ticket is not found."))
                        .build());
        ticketEntity.setStatus(Boolean.FALSE);
        ticketRepository.save(ticketEntity);

        return garageSlotService.removeGarageSlots(ticketEntity.getTicketId());
    }
}
