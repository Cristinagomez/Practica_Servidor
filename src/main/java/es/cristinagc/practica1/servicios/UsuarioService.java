package es.cristinagc.practica1.servicios;

import es.cristinagc.practica1.entidades.Usuario;
import es.cristinagc.practica1.repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final UsuarioRepository repositorio;

    private final PasswordEncoder passwordEncoder;


    public Usuario findById(long id) {
        return repositorio.findById(id).orElse(null);
    }

    public Usuario buscarPorUsername(String username) {
        return repositorio.findFirstByUsername(username);
    }

    public Usuario save(Usuario u) {
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return repositorio.save(u);
    }
    @Transactional
    public void deleteAll() {repositorio.deleteAllInBatch();}
}
