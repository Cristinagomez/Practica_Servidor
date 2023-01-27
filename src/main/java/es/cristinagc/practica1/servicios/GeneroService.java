package es.cristinagc.practica1.servicios;

import es.cristinagc.practica1.entidades.Genero;
import es.cristinagc.practica1.repositorios.GeneroRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class GeneroService {

    private final GeneroRepository repositorio;

    public List<Genero> findAll(){ return repositorio.findAll();}

    public Optional<Genero> findByNombre(String nombre) {
        return repositorio.findByNombre(nombre);
    }

    public void saveAll(List<Genero> lista) {
        repositorio.saveAll(lista);
    }

    public Genero save(Genero g) {
        return repositorio.save(g);
    }

    public Genero findById(long id) {
        return repositorio.findById(id).orElse(null);
    }
    @Transactional
    public void deleteAll() {repositorio.deleteAllInBatch();}

}
