package com.joaoMendes.catalogolivro.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse {

    private Integer status;
    @JsonFormat(pattern = "dd/MM/YYYY")
    private OffsetDateTime dateTime;
    private String message;
    private List<FieldErrorResponse> errors;

    @Data
    public static class FieldErrorResponse {
        private String field;
        private String message;

        public FieldErrorResponse(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }
}
