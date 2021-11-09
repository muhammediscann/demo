package com.example.demo;

import com.example.demo.model.constants.GeneralConstants;
import com.example.demo.model.dto.GarageSlotDto;
import com.example.demo.model.entity.GarageSlotEntity;
import com.example.demo.model.enums.OperationResultCode;
import com.example.demo.model.mapper.GarageSlotMapper;
import com.example.demo.model.response.OperationResult;
import com.example.demo.repository.GarageSlotRepository;
import com.example.demo.service.impl.GarageSlotServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class GarageSlotServiceTest {

    @InjectMocks
    private GarageSlotServiceImpl garageSlotService;

    @Mock
    private GarageSlotRepository garageSlotRepository;

    @Mock
    private GarageSlotMapper garageSlotMapper = Mappers.getMapper(GarageSlotMapper.class);

    private List<GarageSlotDto> garageSlotDtoList = new ArrayList<>();
    private List<GarageSlotEntity> garageSlotEntityList = new ArrayList<>();

    @BeforeEach
    public void initData() {
        for (int index = 1; index <= GeneralConstants.PARK_SLOT; index++) {
            GarageSlotDto garageSlotDto = new GarageSlotDto();
            garageSlotDto.setSlotNumber(index);
            garageSlotDto.setStatus(Boolean.FALSE);
            garageSlotDtoList.add(garageSlotDto);
        }
        garageSlotEntityList = GarageSlotMapper.INSTANCE.toGarageSlotEntityList(garageSlotDtoList);
    }

    @Test
    @DisplayName(value = "saveInitData")
    public void saveInitDataTester() {
        OperationResult expectedResult = OperationResult.newInstance(OperationResultCode.SUCCESS);

        Mockito.when(garageSlotMapper.toGarageSlotEntityList(garageSlotDtoList))
                .thenReturn(garageSlotEntityList);
        Mockito.when(garageSlotRepository.saveAll(Mockito.anyList())).thenReturn(garageSlotEntityList);
        Mockito.when(garageSlotRepository.saveAll(Mockito.anyList())).thenReturn(garageSlotEntityList);

        OperationResult operationResult = garageSlotService.saveInitData();

        Assertions.assertThat(operationResult.getCode()).isEqualTo(expectedResult.getCode());
    }
}
