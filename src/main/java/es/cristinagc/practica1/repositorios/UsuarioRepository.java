package es.cristinagc.practica1.repositorios;

import es.cristinagc.practica1.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findFirstByUsername(String username);
}
