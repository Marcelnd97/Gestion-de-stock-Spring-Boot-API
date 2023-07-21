package com.damo.gestionDeStock.handlers;

import com.damo.gestionDeStock.handlers.exception.EntityNotFoundException;
import com.damo.gestionDeStock.handlers.exception.ErrorCodes;
import com.damo.gestionDeStock.handlers.exception.InvalidEntityException;
import com.damo.gestionDeStock.handlers.exception.InvalideOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handlerException(EntityNotFoundException exception, WebRequest webRequest){

        final HttpStatus notFound = HttpStatus.NOT_FOUND;
        final ErrorDto errorDto = ErrorDto.builder()
                .code(exception.getErrorCodes())
                .HttpCode(notFound.value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDto, notFound);
    }

    @ExceptionHandler(InvalideOperationException.class)
    public ResponseEntity<ErrorDto> handlerException(InvalideOperationException exception, WebRequest webRequest){

        final HttpStatus notFound = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder()
                .code(exception.getErrorCodes())
                .HttpCode(notFound.value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDto, notFound);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorDto> handlerException(InvalidEntityException exception, WebRequest webRequest){

        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        final  ErrorDto errorDto = ErrorDto.builder()
                .code(exception.getErrorCodes())
                .HttpCode(badRequest.value())
                .message(exception.getMessage())
                .errors(exception.getErrors())
                .build();

        return new ResponseEntity<>(errorDto, badRequest);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDto> handlerException(BadCredentialsException exception, WebRequest webRequest){

        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        final  ErrorDto errorDto = ErrorDto.builder()
                .code(ErrorCodes.BAD_CREDENTIALS)
                .HttpCode(badRequest.value())
                .message(exception.getMessage())
                .errors(Collections.singletonList("Login et / ou mot de pass incorrecte"))
                .build();

        return new ResponseEntity<>(errorDto, badRequest);
    }
}
