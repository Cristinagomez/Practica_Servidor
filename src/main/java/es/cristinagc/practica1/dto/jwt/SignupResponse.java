package es.cristinagc.practica1.dto.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupResponse {
  private String username;
  private String role;
  private LocalDateTime fechaAlta;
}
