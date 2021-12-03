package com.example.demo.controller;

import com.example.demo.service.impl.BatchDataTimeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping(path = "/batch-test")
@Slf4j
public class BatchDataTimeTestController {

    private BatchDataTimeService batchDataTimeService;

    @GetMapping
    public ResponseEntity<String> batchDataTest() {
        return ResponseEntity.ok(batchDataTimeService.loadBatch());
    }
}
