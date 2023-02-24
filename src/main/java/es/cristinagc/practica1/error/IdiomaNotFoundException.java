package es.cristinagc.practica1.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdiomaNotFoundException extends RuntimeException{

    public IdiomaNotFoundException(Long id){
        super("No se puede encontrar el idioma con el ID: " + id);
    }
}
