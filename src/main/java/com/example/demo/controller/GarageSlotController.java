package com.example.demo.controller;

import com.example.demo.model.response.GarageSlotStatusResponse;
import com.example.demo.model.response.OperationResult;
import com.example.demo.service.GarageSlotService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@Api(tags = "Garage Slot Service Controller")
@RestController
@RequestMapping(path = "/garage-slot")
@AllArgsConstructor
public class GarageSlotController {
    private final GarageSlotService garageSlotService;

    @GetMapping
    public ResponseEntity<OperationResult> saveInitData() {
        return ResponseEntity.ok(garageSlotService.saveInitData());
    }

    @GetMapping(path = "/status")
    public ResponseEntity<List<GarageSlotStatusResponse>> status() {
        return ResponseEntity.ok(garageSlotService.status());
    }
}
