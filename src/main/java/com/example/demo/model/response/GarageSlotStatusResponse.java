package com.example.demo.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GarageSlotStatusResponse {
    private String plate;
    private String colour;
    private List<Integer> slots;
}
