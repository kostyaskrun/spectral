package com.spectral.demoapplication.model.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ExceptionResponse {
    private String message;
    private Map<String, List<String>> exceptionInfo;
}
