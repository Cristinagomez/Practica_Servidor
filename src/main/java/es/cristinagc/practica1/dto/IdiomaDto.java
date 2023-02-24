package es.cristinagc.practica1.dto;


import lombok.*;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdiomaDto {


    private Long id;
    @NonNull
    private String nombre;
    private List<LibroDto> libros;
}
