package com.example.demo.controller;

import com.example.demo.model.request.ParkRequest;
import com.example.demo.model.response.OperationResult;
import com.example.demo.model.response.TicketResponse;
import com.example.demo.service.TicketService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@Api(tags = "Ticket Service Controller")
@RestController
@RequestMapping(path = "/ticket")
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponse> park(@Valid @RequestBody ParkRequest parkRequest) {
        return ResponseEntity.ok(ticketService.park(parkRequest));
    }

    @GetMapping(path = "/leave")
    public ResponseEntity<OperationResult> leave(@RequestParam Long ticketId) {
        return ResponseEntity.ok(ticketService.leave(ticketId));
    }


}
