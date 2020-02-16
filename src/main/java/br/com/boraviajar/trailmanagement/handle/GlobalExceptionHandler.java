package br.com.boraviajar.trailmanagement.handle;

import br.com.boraviajar.trailmanagement.exception.TrailException;
import br.com.boraviajar.trailmanagement.model.ErrorResponse;
import br.com.boraviajar.trailmanagement.model.FieldResponse;
import br.com.boraviajar.trailmanagement.util.FieldDictionary;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String INVALID_MESSAGE = "Atributos inválidos";
    private static final String INVALID_BODY = "Body inválido";
    private static final String INVALID_CONTENT_TYPE = "Content-Type inválido";

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(final Exception exception, final HttpServletRequest request) {

        return ErrorResponse
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .message(exception.getMessage())
                .timestamp(Instant.now().toEpochMilli())
                .path(request.getServletPath())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleHttpMessageNotReadableException(final HttpMessageNotReadableException exception, final HttpServletRequest request) {

        return ErrorResponse
                .builder()
                .message(INVALID_BODY)
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .error(HttpStatus.UNPROCESSABLE_ENTITY.name())
                .timestamp(Instant.now().toEpochMilli())
                .path(request.getServletPath())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleHttpMediaTypeNotSupportedException(final HttpMediaTypeNotSupportedException exception, final HttpServletRequest request) {

        return ErrorResponse
                .builder()
                .message(INVALID_CONTENT_TYPE)
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .error(HttpStatus.UNPROCESSABLE_ENTITY.name())
                .timestamp(Instant.now().toEpochMilli())
                .path(request.getServletPath())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleMultipartException(final MultipartException exception, final HttpServletRequest request) {

        return ErrorResponse
                .builder()
                .message(INVALID_BODY)
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .error(HttpStatus.UNPROCESSABLE_ENTITY.name())
                .path(request.getServletPath())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleArgumentNotValidException(final MethodArgumentNotValidException exception, final HttpServletRequest request) {

        final ErrorResponse errorResponse = buildResponse(HttpStatus.BAD_REQUEST, request.getServletPath());

        exception.getBindingResult().getFieldErrors().forEach(error -> {

            final FieldResponse field = FieldResponse
                    .builder()
                    .message(error.getDefaultMessage())
                    .field(FieldDictionary.translate(error.getField()))
                    .value(String.valueOf(error.getRejectedValue()))
                    .build();

            errorResponse.addField(field);
        });

        return errorResponse;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(final ConstraintViolationException exception, final HttpServletRequest request) {

        final ErrorResponse errorResponse = buildResponse(HttpStatus.BAD_REQUEST, request.getServletPath());

        exception.getConstraintViolations().forEach(error -> {

            String fieldName = null;

            for (Path.Node node : error.getPropertyPath()) {
                fieldName = node.getName();
            }

            final FieldResponse field = FieldResponse
                    .builder()
                    .message(error.getMessage())
                    .field(FieldDictionary.translate(fieldName))
                    .value(String.valueOf(error.getInvalidValue()))
                    .build();

            errorResponse.addField(field);

        });

        return errorResponse;
    }

    private ErrorResponse buildResponse(final HttpStatus httpStatus, final String path) {

        return ErrorResponse
                .builder()
                .message(INVALID_MESSAGE)
                .timestamp(Instant.now().toEpochMilli())
                .status(httpStatus.value())
                .error(httpStatus.name())
                .path(path)
                .build();
    }

    private String getFieldName(final TypeMismatchException exception) {

        try {
            return String.valueOf(FieldUtils.readField(exception, "name", true));
        } catch (Exception e) {
            return exception.getPropertyName();
        }
    }
}
