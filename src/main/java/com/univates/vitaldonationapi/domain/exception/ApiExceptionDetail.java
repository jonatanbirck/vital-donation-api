package com.univates.vitaldonationapi.domain.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiExceptionDetail {

    private String status; //NOSONAR
    private HttpStatus error;
    private String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    private String message;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> details;

    public ApiExceptionDetail(HttpStatus status, String message) {
        this.status = String.valueOf(status.value());
        this.error = status;
        this.message = message;
    }

    public ApiExceptionDetail(HttpStatus status, String message, List<String> details) {
        this.status = String.valueOf(status.value());
        this.error = status;
        this.message = message;
        this.details = details;
    }

}
