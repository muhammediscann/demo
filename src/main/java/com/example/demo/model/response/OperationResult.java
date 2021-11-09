package com.example.demo.model.response;

import com.example.demo.model.enums.OperationResultCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationResult {
    private OperationResultCode code;
    private String description;

    private OperationResult(OperationResultCode code) {
        this.code = code;
    }

    public static OperationResult newInstance(OperationResultCode operationResultCode) {
        return new OperationResult(operationResultCode);
    }

    public static OperationResult newInstance(OperationResultCode operationResultCode, String description) {
        OperationResult operationResult = newInstance(operationResultCode);
        operationResult.setDescription(description);
        return operationResult;
    }
}
