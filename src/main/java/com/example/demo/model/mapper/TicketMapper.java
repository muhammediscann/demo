package com.example.demo.model.mapper;

import com.example.demo.model.dto.TicketDto;
import com.example.demo.model.entity.TicketEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    TicketDto toTicketDto(TicketEntity source);

    TicketEntity toTicketEntity(TicketDto source);

    List<TicketDto> toTicketDtoList(List<TicketEntity> entityList);

    List<TicketEntity> toTicketEntityList(List<TicketDto> dtoList);
}
