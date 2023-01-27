package es.cristinagc.practica1.repositorios;

import es.cristinagc.practica1.entidades.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeneroRepository extends JpaRepository<Genero, Long> {

    Optional<Genero> findByNombre(String nombre);
}
