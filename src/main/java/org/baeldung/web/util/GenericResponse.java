package org.baeldung.web.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.ObjectError;

public class GenericResponse {
    private String message;
    private String error;

    public GenericResponse(final String message) {
        super();
        this.message = message;
    }

    public GenericResponse(final String message, final String error) {
        super();
        this.message = message;
        this.error = error;
    }

    public GenericResponse(List<ObjectError> allErrors, String error) {
        this.error = error;
        this.message = allErrors.stream()
            .map(e -> e.getDefaultMessage())
            .collect(Collectors.joining(","));
    }

    // public GenericResponse(final List<FieldError> fieldErrors, final List<ObjectError> globalErrors) {
    // super();
    // final ObjectMapper mapper = new ObjectMapper();
    // try {
    // System.out.println("9999" + fieldErrors.get(0)
    // .getDefaultMessage());
    //
    // this.message = mapper.writeValueAsString(fieldErrors);
    // this.error = mapper.writeValueAsString(globalErrors);
    // } catch (final JsonProcessingException e) {
    // this.message = "";
    // this.error = "";
    // }
    // }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

}
