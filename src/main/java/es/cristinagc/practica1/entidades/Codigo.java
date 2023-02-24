package es.cristinagc.practica1.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Codigo {

    @Id
    @GeneratedValue
    private long id;

    @NotEmpty
    private String codigo;
    private String ubicacion;
    @ToString.Exclude
    @OneToOne(mappedBy = "codigo")
    @JoinColumn(unique = true)
    private Libro libro;
}
