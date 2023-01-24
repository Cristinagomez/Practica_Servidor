package es.cristinagc.practica1.config;

import es.cristinagc.practica1.entidades.Codigo;
import es.cristinagc.practica1.entidades.Genero;
import es.cristinagc.practica1.entidades.Idioma;
import es.cristinagc.practica1.entidades.Libro;
import es.cristinagc.practica1.servicios.CodigoService;
import es.cristinagc.practica1.servicios.GeneroService;
import es.cristinagc.practica1.servicios.IdiomaService;
import es.cristinagc.practica1.servicios.LibroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
@EnableJpaAuditing
public class InitialDataConfiguration {

    @Autowired
    LibroService libroService;
    @Autowired
    IdiomaService idiomaService;

    @Autowired
    GeneroService generoService;

    @Autowired
    CodigoService codigoService;

    @PostConstruct
    public void initLibros(){
        libroService.deleteAll();
        idiomaService.deleteAll();
        generoService.deleteAll();
        codigoService.deleteAll();


        Idioma idioma1= Idioma.builder().nombre("Español").build();
        Idioma idioma2= Idioma.builder().nombre("Inglés").build();
        Idioma idioma3= Idioma.builder().nombre("Italiano").build();

        Genero genero1 = Genero.builder().nombre("Fantasía").build();
        Genero genero2 = Genero.builder().nombre("Alegórico").build();

        Codigo codigo1 = Codigo.builder().codigo("FT001").ubicacion("Planta 1 - Estantería 12").build();
        Codigo codigo2 = Codigo.builder().codigo("SC002").ubicacion("Planta 2 - Estantería 8").build();
        Codigo codigo3 = Codigo.builder().codigo("GH001").ubicacion("Planta 1 - Estantería 10").build();
        Codigo codigo4 = Codigo.builder().codigo("PL003").ubicacion("Planta 1 - Estantería 2").build();
        Codigo codigo5 = Codigo.builder().codigo("YD002").ubicacion("Planta 2 - Estantería 6").build();
        Codigo codigo6 = Codigo.builder().codigo("MA002").ubicacion("Planta 1- Estantería 7").build();

        log.info("alta idiomas");
        idiomaService.saveAll(Arrays.asList(idioma1,idioma2,idioma3));

        log.info("alta generos");
        generoService.saveAll(Arrays.asList(genero1,genero2));

        log.info("alta codigos");
        codigoService.saveAll(Arrays.asList(codigo1,codigo2,codigo3,codigo4,codigo5, codigo6));

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
                                .generos(List.of(genero1))
                                .disponible(true)
                                .codigo(codigo1)
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
                                .generos(List.of(genero2))
                                .disponible(false)
                                .codigo(codigo2)
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
                                .generos(List.of(genero2))
                                .disponible(true)
                                .codigo(codigo3)
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
                                .generos(List.of(genero1))
                                .disponible(true)
                                .codigo(codigo4)
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
                                .generos(List.of(genero1,genero2))
                                .disponible(true)
                                .codigo(codigo5)
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
                                .generos(List.of(genero2))
                                .disponible(false)
                                .codigo(codigo6)
                                .cantidad("1")
                                .observaciones("Perdido").build()
                )
        );
    }
}
