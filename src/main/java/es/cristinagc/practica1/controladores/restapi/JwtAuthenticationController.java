package es.cristinagc.practica1.controladores.restapi;

import es.cristinagc.practica1.dto.JpaMapper;
import es.cristinagc.practica1.dto.jwt.JwtResponse;
import es.cristinagc.practica1.dto.jwt.LoginRequest;
import es.cristinagc.practica1.dto.jwt.SignupRequest;
import es.cristinagc.practica1.dto.jwt.SignupResponse;
import es.cristinagc.practica1.entidades.Usuario;
import es.cristinagc.practica1.seguridad.jwt.JwtTokenProvider;
import es.cristinagc.practica1.servicios.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class JwtAuthenticationController {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider tokenProvider;
  private final UserDetailsService userDetailsService;
  private final UsuarioService usuarioService;
  private final JpaMapper mapper;

  @PostMapping(value = "/signin")
  public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest loginRequest) throws Exception {

    try{
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        loginRequest.getUsername(), loginRequest.getPassword()));
    } catch (DisabledException e) {
      throw new Exception("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new Exception("INVALID_CREDENTIALS", e);
    }

    UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
    String token = tokenProvider.generateToken(userDetails);
    String role = userDetails.getAuthorities().stream()
      .map(item -> item.getAuthority())
      .collect(Collectors.joining());

    return ResponseEntity.ok(JwtResponse.builder()
      .token(token)
      .username(userDetails.getUsername())
      .role(role)
      .build());
  }

  @PostMapping("/signup")
  public ResponseEntity<?> register(@RequestBody SignupRequest signupRequest) throws Exception {
    Usuario usuario = usuarioService.save(mapper.toEntity(signupRequest));
    SignupResponse signupResponse = mapper.toDto(usuario);
    return ResponseEntity.ok(signupResponse);
  }

}
