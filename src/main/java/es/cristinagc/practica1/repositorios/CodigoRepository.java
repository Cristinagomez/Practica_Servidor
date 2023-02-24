package es.cristinagc.practica1.repositorios;

import es.cristinagc.practica1.entidades.Codigo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CodigoRepository extends JpaRepository<Codigo, Long> {

    List<Codigo> findAllByLibroIsNull();

    Optional<Codigo> findByLibroId(Long id);


}
