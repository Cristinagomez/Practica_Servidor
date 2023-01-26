package es.cristinagc.practica1.controladores;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

  private static final String CONTADOR_NAME = "num_visitas";


  @GetMapping({"", "index"})
  public String welcome(HttpServletRequest request, HttpServletResponse response, Model model) {
    log.info("Página de bienvenida");

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      String usuario = authentication.getName();

      HttpSession session = request.getSession();
      boolean logueado = (session.getAttribute(CONTADOR_NAME) != null);

      if (!logueado) {
        Optional<Cookie> cookieEncontrada = Arrays.stream(request.getCookies())
                .filter(cookie -> usuario.equals(cookie.getName()))
                .findAny();


        int contador = 1;
        if (cookieEncontrada.isEmpty()) {
          Cookie cookie = new Cookie(usuario, "1");
          cookie.setPath("/");
          cookie.setDomain("localhost");
          cookie.setMaxAge(7 * 24 * 60 * 60);  // 7 días
          cookie.setSecure(true);
          cookie.setHttpOnly(true);
          response.addCookie(cookie);

        } else {
          Cookie cookie = cookieEncontrada.get();
          contador = Integer.parseInt(cookie.getValue()) + 1;
          cookie.setValue(String.valueOf(contador));
          response.addCookie(cookie);
        }

        session.setAttribute(CONTADOR_NAME, contador);
        log.info("id de la sesión: {}", session.getId());
      }

    }
    return "index";
  }

  @GetMapping("/login")
  public String login(){
    return "login";
  }

  @GetMapping("/forbidden")
  public String forbidden(){
    return "forbidden";
  }

  @GetMapping("/privacidad")
  public String privacidad(){
    return "privacidad";
  }

  @GetMapping("/aviso")
  public String aviso(){
    return "aviso";
  }
}
