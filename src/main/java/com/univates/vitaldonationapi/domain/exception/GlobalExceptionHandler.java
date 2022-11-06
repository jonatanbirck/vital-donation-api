package com.univates.vitaldonationapi.domain.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.univates.vitaldonationapi.domain.exception.model.AlreadyExistsException;
import com.univates.vitaldonationapi.domain.exception.model.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ApiExceptionDetail handleException(Exception e) {
        return new ApiExceptionDetail(INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ResponseStatus(FORBIDDEN)
    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    public ApiExceptionDetail handleException(AccessDeniedException e) {
        return new ApiExceptionDetail(FORBIDDEN, e.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiExceptionDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return valueOf(e.getBindingResult());
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiExceptionDetail handleUsernameNotFoundException(IllegalArgumentException e) {
        return new ApiExceptionDetail(BAD_REQUEST, e.getMessage());
    }

    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(UsernameNotFoundException.class)
    public ApiExceptionDetail handleUsernameNotFoundException(UsernameNotFoundException e) {
        return new ApiExceptionDetail(NOT_FOUND, e.getMessage());
    }

    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    public ApiExceptionDetail handleUserNotFoundException(NotFoundException e) {
        return new ApiExceptionDetail(NOT_FOUND, e.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(AlreadyExistsException.class)
    public ApiExceptionDetail handleUserNotFoundException(AlreadyExistsException e) {
        return new ApiExceptionDetail(BAD_REQUEST, e.getMessage());
    }

    public static void responseException(HttpServletResponse response, Exception e) throws IOException {
        response.setStatus(FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        var exceptionDetail = new ApiExceptionDetail(FORBIDDEN, e.getMessage());
        new ObjectMapper().writeValue(response.getOutputStream(), exceptionDetail);
    }

    public static ApiExceptionDetail valueOf(BindingResult bindingResult) {
        List<String> details = bindingResult.getAllErrors().stream()
                .map(FieldError.class::cast)
                .map(errorDetail -> errorDetail.getField() + ": " + errorDetail.getDefaultMessage())
                .toList();
        return new ApiExceptionDetail(HttpStatus.BAD_REQUEST, "problems validating fields", details);
    }
}
