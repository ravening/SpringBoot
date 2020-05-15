package com.rakeshv.networkoverview.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExecutionStatus {
    private boolean status;
    private String message;
    private HttpStatus httpStatus;
}
