package es.cristinagc.practica1.repositorios;

import es.cristinagc.practica1.entidades.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
}
