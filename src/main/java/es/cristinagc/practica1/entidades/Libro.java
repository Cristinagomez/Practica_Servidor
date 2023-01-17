package es.cristinagc.practica1.entidades;

/**
 * @author Cristina Gómez Campos
 */


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Libro {

    @Id
    @GeneratedValue
    private long id;
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
    //@NotNull(message = "{libro.genero.notnull}")
    private Genero genero;
    private boolean disponible;
    @Pattern(regexp = "^[1-9]{1}", message = "{libro.cantidad.digitos}")
    private String cantidad;
    @Size(max = 50, message = "{libro.observaciones.max}")
    private String observaciones;



}
