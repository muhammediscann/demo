package com.example.demo.service.impl;

import com.example.demo.exception.OperationResultException;
import com.example.demo.model.constants.GeneralConstants;
import com.example.demo.model.dto.GarageSlotDto;
import com.example.demo.model.dto.TicketDto;
import com.example.demo.model.entity.GarageSlotEntity;
import com.example.demo.model.entity.TicketEntity;
import com.example.demo.model.enums.OperationResultCode;
import com.example.demo.model.mapper.GarageSlotMapper;
import com.example.demo.model.response.GarageSlotStatusResponse;
import com.example.demo.model.response.OperationResult;
import com.example.demo.repository.GarageSlotRepository;
import com.example.demo.service.GarageSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@RequiredArgsConstructor
@Service
public class GarageSlotServiceImpl implements GarageSlotService {

    private final GarageSlotRepository garageSlotRepository;

    @Transactional
    @Override
    public OperationResult saveInitData() {
        List<GarageSlotDto> garageSlotDtoList = new ArrayList<>();
        for (int index = 1; index <= GeneralConstants.PARK_SLOT; index++) {
            GarageSlotDto garageSlotDto = new GarageSlotDto();
            garageSlotDto.setSlotNumber(index);
            garageSlotDto.setStatus(Boolean.FALSE);
            garageSlotDtoList.add(garageSlotDto);
        }

        return saveInitData(garageSlotDtoList);
    }

    @Transactional(readOnly = true)
    @Override
    public List<GarageSlotDto> findEmptySlots() {
        List<GarageSlotEntity> slotEntities = garageSlotRepository.findAllByStatusOrderBySlotNumber(Boolean.FALSE);
        return Optional.ofNullable(slotEntities)
                .map(GarageSlotMapper.INSTANCE::toGarageSlotDtoList)
                .orElseThrow(() -> OperationResultException.builder()
                        .operationResult(OperationResult.newInstance(OperationResultCode.ERROR, "Garage is full!"))
                        .build());
    }

    @Transactional
    @Override
    public List<GarageSlotDto> saveGarageSlot(Integer carSlot,
                                              Integer startNumber,
                                              List<GarageSlotDto> garageSlotDtoList,
                                              TicketDto ticketDto) {

        List<GarageSlotDto> willUpdateSlotList = new ArrayList<>();
        for (int i = 0; i < carSlot; i++) {
            Integer finalStartNumber = startNumber;
            GarageSlotDto slotDto = garageSlotDtoList.stream()
                    .filter(garageSlotDto -> garageSlotDto.getSlotNumber().equals(finalStartNumber))
                    .findAny()
                    .orElse(null);
            if (Objects.nonNull(slotDto)) {
                slotDto.setStatus(Boolean.TRUE);
                slotDto.setTicket(ticketDto);
                willUpdateSlotList.add(slotDto);
                startNumber++;
            }
        }

        List<GarageSlotEntity> garageSlotEntities = GarageSlotMapper.INSTANCE
                .toGarageSlotEntityList(willUpdateSlotList);
        List<GarageSlotEntity> slotEntityList = garageSlotRepository.saveAll(garageSlotEntities);
        return GarageSlotMapper.INSTANCE.toGarageSlotDtoList(slotEntityList);
    }

    @Transactional
    @Override
    public OperationResult removeGarageSlots(Long ticketId) {
        List<GarageSlotEntity> garageSlotEntityList = garageSlotRepository.findAllByTicketTicketId(ticketId);
        garageSlotEntityList.forEach(garageSlotEntity -> {
            garageSlotEntity.setTicket(null);
            garageSlotEntity.setStatus(Boolean.FALSE);
        });
        garageSlotRepository.saveAll(garageSlotEntityList);
        return OperationResult.newInstance(OperationResultCode.SUCCESS);
    }

    @Override
    public List<GarageSlotStatusResponse> status() {
        List<GarageSlotStatusResponse> responseList = new ArrayList<>();
        List<GarageSlotEntity> garageSlotEntities = garageSlotRepository.findAllByStatusOrderBySlotNumber(Boolean.TRUE);
        Map<TicketEntity, List<GarageSlotEntity>> ticketEntityListMap = garageSlotEntities.stream()
                .collect(groupingBy(GarageSlotEntity::getTicket));
        ticketEntityListMap.forEach((ticketEntity, garageSlotEntities1) -> {
            GarageSlotStatusResponse garageSlotStatusResponse = GarageSlotStatusResponse.builder()
                    .plate(ticketEntity.getPlate())
                    .colour(ticketEntity.getColour())
                    .slots(garageSlotEntities1.stream().map(GarageSlotEntity::getSlotNumber).collect(Collectors.toList()))
                    .build();
            responseList.add(garageSlotStatusResponse);
        });
        return responseList;
    }

    private OperationResult saveInitData(List<GarageSlotDto> garageSlotDtoList) {
        List<GarageSlotEntity> garageSlotEntityList = GarageSlotMapper.INSTANCE.toGarageSlotEntityList(garageSlotDtoList);
        garageSlotRepository.saveAll(garageSlotEntityList);
        return OperationResult.newInstance(OperationResultCode.SUCCESS);
    }
}
