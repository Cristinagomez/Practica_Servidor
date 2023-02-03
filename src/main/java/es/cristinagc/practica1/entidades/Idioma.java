package es.cristinagc.practica1.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class Idioma {

    @Id
    @GeneratedValue
    private long id;
    @NotEmpty
    private String nombre;
    @JsonIgnore
    @OneToMany(mappedBy = "idioma", fetch = FetchType.EAGER)
    private List<Libro> libros;
}
