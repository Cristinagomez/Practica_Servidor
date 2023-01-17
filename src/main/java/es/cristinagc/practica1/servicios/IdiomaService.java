package es.cristinagc.practica1.servicios;

import es.cristinagc.practica1.entidades.Idioma;
import es.cristinagc.practica1.repositorios.IdiomaRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class IdiomaService {
    private final IdiomaRepository repositorio;

    public List<Idioma> findAll(){ return repositorio.findAll();}

    public void saveAll(List<Idioma> lista) {
        repositorio.saveAll(lista);
    }

    public Idioma findById(long id) {
        return repositorio.findById(id).orElse(null);
    }

    public void deleteAll() {repositorio.deleteAllInBatch();}
}
