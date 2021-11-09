package com.example.demo.exception;

import com.example.demo.model.response.OperationResult;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationResultException extends RuntimeException {
    private OperationResult operationResult;
}
