package es.cristinagc.practica1.servicios;

import es.cristinagc.practica1.entidades.Idioma;
import es.cristinagc.practica1.entidades.Libro;
import es.cristinagc.practica1.repositorios.IdiomaRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class IdiomaService {
    private final IdiomaRepository repositorio;

    public List<Idioma> findAll(){ return repositorio.findAll();}

    public void saveAll(List<Idioma> lista) {
        repositorio.saveAll(lista);
    }

    public Optional<Idioma> findById(long id) {
        return repositorio.findById(id);
    }

    public Idioma save(Idioma i) {
        List<Libro> libros = i.getLibros();
        if (!libros.isEmpty()) {
            libros.forEach(libro -> libro.setIdioma(i));
        }
        return repositorio.save(i);
    }
    @Transactional
    public void deleteAll() {repositorio.deleteAllInBatch();}

    public void deleteById(Long id) {
        repositorio.deleteById(id);
    }
}
