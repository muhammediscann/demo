package com.example.demo.service.impl;

import com.example.demo.model.entity.BatchDataTimeTest;
import com.example.demo.repository.BatchDataTimeTestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class BatchDataTimeService {

    private BatchDataTimeTestRepository batchDataTimeTestRepository;

    public String loadBatch() {
        List<BatchDataTimeTest> batchDataTimeTestList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            batchDataTimeTestList
                    .add(BatchDataTimeTest.builder()
                            .batchName("Ali" + i)
                            .status("NEW")
                            .build());
        }
        long startTime = System.currentTimeMillis();
        batchDataTimeTestRepository.saveAll(batchDataTimeTestList);
        return "Toplam Geçen süre : " + (System.currentTimeMillis() - startTime) / 1000;
    }
}
