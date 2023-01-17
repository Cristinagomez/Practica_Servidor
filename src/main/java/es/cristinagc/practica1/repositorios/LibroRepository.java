package es.cristinagc.practica1.repositorios;

import es.cristinagc.practica1.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long> {

    List<Libro> findByTituloContainsIgnoreCase(String filtro);

    List<Libro> findByAutorContainsIgnoreCase(String filtro);

    @Query(value="SELECT * FROM LIBRO WHERE LOWER(TITULO) LIKE CONCAT('%',?1,'%') OR LOWER(AUTOR) LIKE CONCAT('%',?1,'%')", nativeQuery=true)
    List<Libro> encuentraPorTituloAutorNativa(String cadena);

}
