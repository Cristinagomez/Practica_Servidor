package es.cristinagc.practica1.servicios;


import es.cristinagc.practica1.entidades.Codigo;
import es.cristinagc.practica1.repositorios.CodigoRepository;

import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class CodigoService {

    private final CodigoRepository repositorio;

    public List<Codigo> findAll(){ return repositorio.findAll();}

    public List<Codigo> findDisponibles () {
        return repositorio.findAllByLibroIsNull();
    }

    public Optional<Codigo> findByLibroId(long id) {
        return repositorio.findByLibroId(id);
    }
    public void saveAll(List<Codigo> lista) {
        repositorio.saveAll(lista);
    }

    public Codigo save(Codigo c) {
        return repositorio.save(c);
    }

    public Codigo findById(long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Transactional
    public void deleteAll() {repositorio.deleteAllInBatch();}
}
