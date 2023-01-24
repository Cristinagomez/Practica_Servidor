package es.cristinagc.practica1.servicios;


import es.cristinagc.practica1.entidades.Codigo;
import es.cristinagc.practica1.repositorios.CodigoRepository;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class CodigoService {

    private final CodigoRepository repositorio;

    public List<Codigo> findAll(){ return repositorio.findAll();}

    public void saveAll(List<Codigo> lista) {
        repositorio.saveAll(lista);
    }

    public Codigo findById(long id) {
        return repositorio.findById(id).orElse(null);
    }

    public void deleteAll() {repositorio.deleteAllInBatch();}
}
