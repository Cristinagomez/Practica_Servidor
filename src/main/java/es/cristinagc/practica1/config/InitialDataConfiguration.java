package es.cristinagc.practica1.config;

import es.cristinagc.practica1.entidades.Genero;
import es.cristinagc.practica1.entidades.Idioma;
import es.cristinagc.practica1.entidades.Libro;
import es.cristinagc.practica1.servicios.GeneroService;
import es.cristinagc.practica1.servicios.IdiomaService;
import es.cristinagc.practica1.servicios.LibroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Slf4j
@Configuration
@EnableJpaAuditing
public class InitialDataConfiguration {

    @Autowired
    LibroService libroService;
    @Autowired
    IdiomaService idiomaService;

    @PostConstruct
    public void initLibros(){
        libroService.deleteAll();
        idiomaService.deleteAll();

        Idioma idioma1= Idioma.builder().nombre("Español").build();
        Idioma idioma2= Idioma.builder().nombre("Inglés").build();
        Idioma idioma3= Idioma.builder().nombre("Italiano").build();

        log.info("alta idiomas");
        idiomaService.saveAll(Arrays.asList(idioma1,idioma2,idioma3));

        log.info("alta libros");
        libroService.saveAll(
                Arrays.asList(
                        Libro.builder()
                                .id(1)
                                .titulo("Drácula")
                                .autor("Bram Stoker")
                                .anioPublicacion("1897")
                                .anioEdicion("2019")
                                .editorial("Debolsillo")
                                .idioma(idioma1)
                                .genero(Genero.ALEGÓRICO)
                                .disponible(true)
                                .cantidad("2")
                                .observaciones("").build(),
                        Libro.builder()
                                .id(2)
                                .titulo("El resplandor")
                                .autor("Stephen King")
                                .anioPublicacion("1977")
                                .anioEdicion("2006")
                                .editorial("Burlington")
                                .idioma(idioma2)
                                .genero(Genero.ALEGÓRICO)
                                .disponible(false)
                                .cantidad("1")
                                .observaciones("El ejemplar está dañado").build(),
                        Libro.builder()
                                .id(3)
                                .titulo("La sombra del viento")
                                .autor("Carlos Ruiz Zafón")
                                .anioPublicacion("2001")
                                .anioEdicion("2002")
                                .editorial("Planeta")
                                .idioma(idioma3)
                                .genero(Genero.ALEGÓRICO)
                                .disponible(true)
                                .cantidad("4")
                                .observaciones("").build(),
                        Libro.builder()
                                .id(4)
                                .titulo("The Hobbit")
                                .autor("J.R.R. Tolkien")
                                .anioPublicacion("1937")
                                .anioEdicion("2012")
                                .editorial("Houghton Mifflin Harcourt")
                                .idioma(idioma1)
                                .genero(Genero.ALEGÓRICO)
                                .disponible(true)
                                .cantidad("2")
                                .observaciones("").build(),
                        Libro.builder()
                                .id(5)
                                .titulo("Le Petit Prince")
                                .autor("Antoine de Saint-Exupery")
                                .anioPublicacion("1943")
                                .anioEdicion("2016")
                                .editorial("Gallimard")
                                .idioma(idioma3)
                                .genero(Genero.ALEGÓRICO)
                                .disponible(true)
                                .cantidad("6")
                                .observaciones("").build(),
                        Libro.builder()
                                .id(6)
                                .titulo("Phantom of the Opera")
                                .autor("G. Leroux")
                                .anioPublicacion("1910")
                                .anioEdicion("2005")
                                .editorial("Macmillan Readers")
                                .idioma(idioma2)
                                .genero(Genero.ALEGÓRICO)
                                .disponible(false)
                                .cantidad("1")
                                .observaciones("Perdido").build()
                )
        );
    }
}
