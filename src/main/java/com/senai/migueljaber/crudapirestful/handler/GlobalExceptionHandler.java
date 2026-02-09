package com.senai.migueljaber.crudapirestful.handler;

import com.senai.migueljaber.crudapirestful.exception.ApiError;
import com.senai.migueljaber.crudapirestful.exception.DadosInvalidosException;
import com.senai.migueljaber.crudapirestful.exception.RegistroNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<ApiError> handleNaoEncontrado(RegistroNaoEncontradoException ex, HttpServletRequest req) {
        ApiError body = new ApiError(404, "NOT_FOUND", ex.getMessage(), req.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(DadosInvalidosException.class)
    public ResponseEntity<ApiError> handleDadosInvalidos(DadosInvalidosException ex, HttpServletRequest req) {
        ApiError body = new ApiError(400, "BAD_REQUEST", ex.getMessage(), req.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}