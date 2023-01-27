package es.cristinagc.practica1.entidades;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class Genero {

    @Id
    @GeneratedValue
    private long id;
    @NotEmpty
    private String nombre;

    @ManyToMany(mappedBy = "generos")
    private Set<Libro> libros;
}

