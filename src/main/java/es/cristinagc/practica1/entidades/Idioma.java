package es.cristinagc.practica1.entidades;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

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
}
