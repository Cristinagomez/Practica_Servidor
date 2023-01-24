package es.cristinagc.practica1.servicios;

/**
 * @author Cristina Gómez Campos
 */

import es.cristinagc.practica1.entidades.Libro;
import es.cristinagc.practica1.repositorios.LibroRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class LibroService {

    private final LibroRepository repositorio;

    public Libro add(Libro l) {
        return repositorio.save(l);
    }

    public List<Libro> findAll() {
        return repositorio.findAll();
    }

    public Libro findById(long id) {
        return repositorio.findById(id).orElse(null);
    }

    public List<Libro> findByTitulo(String filtro) {
        List<Libro> result = repositorio.findByTituloContainsIgnoreCase(filtro);

        return result;
    }

    public List<Libro> findByAutor(String filtro) {
        List<Libro> result = repositorio.findByAutorContainsIgnoreCase(filtro);

        return result;
    }

    public List<Libro> findByAutorOrTittle(String filtro) {
        List<Libro> result = repositorio.encuentraPorTituloAutorNativa(filtro);

        return result;
    }
    public List<Libro> buscador(String filtro) {
        List<Libro> result = repositorio.encuentraPorTituloAutorNativa(filtro);

        return result;
    }



    public Libro edit(Libro l) {
        return repositorio.save(l);
    }

    public void delete(Libro l) {
        repositorio.delete(l);
    }


    public void saveAll(List<Libro> lista) {
        repositorio.saveAll(lista);
    }

    public void deleteAll() {repositorio.deleteAll();}



}
