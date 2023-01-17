package es.cristinagc.practica1.entidades;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class Genero1 {

    @Id
    @GeneratedValue
    private long id;
    @NotEmpty
    private String nombre;
}
