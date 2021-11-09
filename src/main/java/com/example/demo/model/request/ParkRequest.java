package com.example.demo.model.request;

import com.example.demo.model.enums.CarType;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel
public class ParkRequest {
    @NotBlank
    @NotNull(message = "Plate Number mandotory")
    private String plate;
    @NotNull(message = "Car Type mandotory")
    private CarType carType;
    @NotBlank
    @NotNull(message = "Colour mandotory")
    private String colour;
}
