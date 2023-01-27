package es.cristinagc.practica1.entidades;

/**
 * @author Cristina Gómez Campos
 */


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Libro {

    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty(message = "{libro.titulo.notempty}")
    private String titulo;
    @NotEmpty(message = "{libro.autor.notemty}")
    private String autor;
    @Pattern(regexp = "^[0-9]{4}", message = "{libro.anio.digitos}")
    private String anioPublicacion;
    @Pattern(regexp = "^[0-9]{4}", message = "{libro.anio.digitos}")
    private String anioEdicion;
    @Pattern(regexp = "^[a-zA-Z ]{2,254}", message = "{libro.editorial.letras}")
    private String editorial;
    @ManyToOne
    private Idioma idioma;


    @ManyToMany
    @JoinTable(name = "libro_genero",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "genero_id")
    )
    private Set<Genero> generos;

    private boolean disponible;
    @Size(max = 50, message = "{libro.observaciones.max}")
    private String observaciones;
    @OneToOne
    private Codigo codigo;




}
