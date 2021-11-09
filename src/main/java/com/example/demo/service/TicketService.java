package com.example.demo.service;

import com.example.demo.model.request.ParkRequest;
import com.example.demo.model.response.OperationResult;
import com.example.demo.model.response.TicketResponse;

public interface TicketService {

    TicketResponse park(ParkRequest parkRequest);

    OperationResult leave(Long ticketId);
}
