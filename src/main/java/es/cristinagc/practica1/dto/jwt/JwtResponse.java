package es.cristinagc.practica1.dto.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {
  private String token;
  @Builder.Default
  private String tokenType = "Bearer";
  private String username;
  private String role;
}
