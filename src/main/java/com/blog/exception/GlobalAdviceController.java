package com.blog.exception;

import com.blog.core.domain.Error;
import com.blog.core.domain.ErrorResponse;
import com.blog.core.domain.StandardMessage;
import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalAdviceController {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception e, HttpServletRequest request) {
        return StandardMessage.createStandardErrorMessage(Error.INTERNAL_SERVER_ERROR, request.getRequestURI());
    }

    @ExceptionHandler(DataSourceException.class)
    public ResponseEntity<ErrorResponse> dataSourceException(DataSourceException e, HttpServletRequest request) {
        return StandardMessage.createStandardErrorMessage(Error.PROVIDER_NOT_FOUND, request.getRequestURI(), e.getMessage());
    }

    @ExceptionHandler(NotSupportProviderException.class)
    public ResponseEntity<ErrorResponse> notSupportProviderHandler(NotSupportProviderException e, HttpServletRequest request) {
        return StandardMessage.createStandardErrorMessage(Error.PROVIDER_NOT_FOUND, request.getRequestURI(), e.getMessage());
    }

    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> paramValidationHandler(BindException e, HttpServletRequest request) {
        e.printStackTrace();
        return StandardMessage.createStandardErrorMessage(
                Error.BAD_REQUEST_ERROR,
                makeResultMessage(fieldErrorList(e.getBindingResult().getFieldErrors())),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> validHandler(ConstraintViolationException e,
                                                      HttpServletRequest request) {
        return StandardMessage.createStandardErrorMessage(
                Error.BAD_REQUEST_ERROR,
                makeResultMessage(constraintViolationList(new ArrayList<>(e.getConstraintViolations()))),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> argumentHandler(MethodArgumentTypeMismatchException e,
                                                         HttpServletRequest request) {
        MethodParameter parameter = e.getParameter();
        return StandardMessage.createStandardErrorMessage(
                Error.BAD_REQUEST_ERROR,
                MessageFormat.format(
                        "{0}은 {1}형태여야 합니다.", parameter.getParameterName(), parameter.getGenericParameterType()
                ),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> argumentHandler(MissingServletRequestParameterException e,
                                                         HttpServletRequest request) {
        return StandardMessage.createStandardErrorMessage(
                Error.BAD_REQUEST_ERROR,
                MessageFormat.format("{0}은 필수 파라미터 입니다.", e.getParameterName()),
                request.getRequestURI()
        );
    }

    private List<List<String>> constraintViolationList(List<ConstraintViolation<?>> errors) {
        return errors.stream()
                .map((error) -> List.of(
                        error.getPropertyPath().toString(),
                        String.valueOf(error.getInvalidValue()),
                        error.getMessage()
                ))
                .collect(Collectors.toList());
    }

    private List<List<String>> fieldErrorList(List<FieldError> errors) {
        return errors.stream()
                .map((error) -> List.of(
                        error.getField(),
                        String.valueOf(error.getRejectedValue()),
                        String.valueOf(error.getDefaultMessage())
                ))
                .collect(Collectors.toList());
    }

    private List<String> makeResultMessage(List<List<String>> errors) {
        return errors.stream()
                .map(List::toArray)
                .map(this::makeErrorMessage)
                .collect(Collectors.toList());
    }

    private String makeErrorMessage(Object[] errors) {
        return MessageFormat.format("{0}은(는) {1}입니다. {2}", errors);
    }
}
