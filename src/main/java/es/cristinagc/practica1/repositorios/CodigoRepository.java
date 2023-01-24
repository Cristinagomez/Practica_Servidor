package es.cristinagc.practica1.repositorios;

import es.cristinagc.practica1.entidades.Codigo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodigoRepository extends JpaRepository<Codigo, Long> {
}
