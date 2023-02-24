package es.cristinagc.practica1.error;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiError {

  @NonNull
  private HttpStatus estado;
  @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
  @Builder.Default
  private LocalDateTime fecha = LocalDateTime.now();
  @NonNull
  private String mensaje;

}
