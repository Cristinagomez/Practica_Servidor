package es.cristinagc.practica1.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibroDto {

    private Long id;
    @NonNull
    private String titulo;
    private String autor;
    private String anioPublicacion;
    private String anioEdicion;
    private String editorial;
    @JsonIgnore
    private IdiomaDto idioma;
    private boolean disponible;
    private String observaciones;

}
