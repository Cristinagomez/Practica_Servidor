package es.cristinagc.practica1.seguridad.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.cristinagc.practica1.error.ApiError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
  private final ObjectMapper mapper;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
                       AuthenticationException authException) throws IOException {

    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType("application/json");

    ApiError apiError = ApiError.builder()
      .estado(HttpStatus.UNAUTHORIZED)
      .mensaje(authException.getMessage())
      .build();
    String strApiError = mapper.writeValueAsString(apiError);

    PrintWriter writer = response.getWriter();
    writer.println(strApiError);

  }
}
