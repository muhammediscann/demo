package com.example.demo.model.mapper;

import com.example.demo.model.dto.GarageSlotDto;
import com.example.demo.model.entity.GarageSlotEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GarageSlotMapper {

    GarageSlotMapper INSTANCE = Mappers.getMapper(GarageSlotMapper.class);

    GarageSlotDto toGarageSlotDto(GarageSlotEntity source);

    GarageSlotEntity toGarageSlotEntity(GarageSlotDto source);

    List<GarageSlotDto> toGarageSlotDtoList(List<GarageSlotEntity> entityList);

    List<GarageSlotEntity> toGarageSlotEntityList(List<GarageSlotDto> dtoList);
}