package com.africa.semicolon.data.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
@Getter
@AllArgsConstructor
public class ApiResponse {
    private boolean isSuccessful;
    private Object data;
}
