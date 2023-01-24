package es.cristinagc.practica1.servicios;

import es.cristinagc.practica1.entidades.Genero;
import es.cristinagc.practica1.repositorios.GeneroRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class GeneroService {

    private final GeneroRepository repositorio;

    public List<Genero> findAll(){ return repositorio.findAll();}

    public void saveAll(List<Genero> lista) {
        repositorio.saveAll(lista);
    }

    public Genero findById(long id) {
        return repositorio.findById(id).orElse(null);
    }

    public void deleteAll() {repositorio.deleteAllInBatch();}

}
