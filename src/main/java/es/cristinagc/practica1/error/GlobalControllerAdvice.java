package es.cristinagc.practica1.error;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers,
                                                                HttpStatus status, WebRequest request) {
    Map<String, List<String>> respuesta = new HashMap<>();
    List<String> errores = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());
    respuesta.put("errores", errores);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<?> constraintViolationException(ConstraintViolationException ex, WebRequest request) {

    List<String> errores = new ArrayList<>();
    ex.getConstraintViolations().forEach(cv -> errores.add(cv.getMessage()));
    Map<String, List<String>> respuesta = new HashMap<>();
    respuesta.put("errores", errores);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
  }
}
