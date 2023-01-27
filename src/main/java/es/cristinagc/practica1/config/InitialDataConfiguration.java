package es.cristinagc.practica1.config;

import es.cristinagc.practica1.entidades.*;
import es.cristinagc.practica1.servicios.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Set;

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

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PreferenciasService preferenciasService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initUsuarios() {
        preferenciasService.deleteAll();
        usuarioService.deleteAll();

        Usuario usuario1 = Usuario.builder()
                .username("user").password(passwordEncoder.encode("user")).role("ROLE_USER").build();
        Preferencias prefs1 = Preferencias.builder().darkMode(true).idioma("en_US").usuario(usuario1).build();
        preferenciasService.save(prefs1);
        //usuario1 = usuarioService.registrar(usuario1);

        Usuario usuario2 = Usuario.builder()
                .username("admin").password(passwordEncoder.encode("admin")).role("ROLE_ADMIN").build();
        Preferencias prefs2 = Preferencias.builder().darkMode(false).idioma("es_ES").usuario(usuario2).build();
        preferenciasService.save(prefs2);
        //usuario2 = usuarioService.registrar(usuario2);
        Usuario usuario3 = Usuario.builder()
                .username("invitado").password(passwordEncoder.encode("invitado")).role("ROLE_INVITADO").build();
        Preferencias prefs3 = Preferencias.builder().darkMode(true).idioma("es_ES").usuario(usuario3).build();
        preferenciasService.save(prefs3);
        //usuario2 = usuarioService.registrar(usuario3);

    }
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
        Genero genero3 = Genero.builder().nombre("Drama").build();
        Genero genero4 = Genero.builder().nombre("Novela Negra").build();


        Codigo codigo1 = Codigo.builder().codigo("FT001").ubicacion("Planta 1 - Estantería 12").build();
        Codigo codigo2 = Codigo.builder().codigo("SC002").ubicacion("Planta 2 - Estantería 8").build();
        Codigo codigo3 = Codigo.builder().codigo("GH001").ubicacion("Planta 1 - Estantería 10").build();
        Codigo codigo4 = Codigo.builder().codigo("PL003").ubicacion("Planta 1 - Estantería 2").build();
        Codigo codigo5 = Codigo.builder().codigo("YD002").ubicacion("Planta 2 - Estantería 6").build();
        Codigo codigo6 = Codigo.builder().codigo("MA002").ubicacion("Planta 1- Estantería 7").build();
        Codigo codigo7 = Codigo.builder().codigo("HX005").ubicacion("Planta 1- Estantería 1").build();
        Codigo codigo8 = Codigo.builder().codigo("BZ004").ubicacion("Planta 2- Estantería 3").build();


        log.info("alta idiomas");
        idiomaService.saveAll(Arrays.asList(idioma1,idioma2,idioma3));

        log.info("alta generos");
        generoService.saveAll(Arrays.asList(genero1,genero2,genero3,genero4));

        log.info("alta codigos");
        codigoService.saveAll(Arrays.asList(codigo1,codigo2,codigo3,codigo4,codigo5, codigo6, codigo7,codigo8));

        log.info("alta libros");
        libroService.saveAll(
                Arrays.asList(
                        Libro.builder()
                                .id(1L)
                                .titulo("Drácula")
                                .autor("Bram Stoker")
                                .anioPublicacion("1897")
                                .anioEdicion("2019")
                                .editorial("Debolsillo")
                                .idioma(idioma1)
                                .generos(Set.of(genero1))
                                .disponible(true)
                                .codigo(codigo1)
                                .observaciones("").build(),
                        Libro.builder()
                                .id(2L)
                                .titulo("El resplandor")
                                .autor("Stephen King")
                                .anioPublicacion("1977")
                                .anioEdicion("2006")
                                .editorial("Burlington")
                                .idioma(idioma2)
                                .generos(Set.of(genero2))
                                .disponible(false)
                                .codigo(codigo2)
                                .observaciones("El ejemplar está dañado").build(),
                        Libro.builder()
                                .id(3L)
                                .titulo("La sombra del viento")
                                .autor("Carlos Ruiz Zafón")
                                .anioPublicacion("2001")
                                .anioEdicion("2002")
                                .editorial("Planeta")
                                .idioma(idioma3)
                                .generos(Set.of(genero2))
                                .disponible(true)
                                .codigo(codigo3)
                                .observaciones("").build(),
                        Libro.builder()
                                .id(4L)
                                .titulo("The Hobbit")
                                .autor("J.R.R. Tolkien")
                                .anioPublicacion("1937")
                                .anioEdicion("2012")
                                .editorial("Houghton Mifflin Harcourt")
                                .idioma(idioma1)
                                .generos(Set.of(genero1))
                                .disponible(true)
                                .codigo(codigo4)
                                .observaciones("").build(),
                        Libro.builder()
                                .id(5L)
                                .titulo("Le Petit Prince")
                                .autor("Antoine de Saint-Exupery")
                                .anioPublicacion("1943")
                                .anioEdicion("2016")
                                .editorial("Gallimard")
                                .idioma(idioma3)
                                .generos(Set.of(genero1,genero2))
                                .disponible(true)
                                .codigo(codigo5)
                                .observaciones("").build(),
                        Libro.builder()
                                .id(6L)
                                .titulo("Phantom of the Opera")
                                .autor("G. Leroux")
                                .anioPublicacion("1910")
                                .anioEdicion("2005")
                                .editorial("Macmillan Readers")
                                .idioma(idioma2)
                                .generos(Set.of(genero2))
                                .disponible(false)
                                .codigo(codigo6)
                                .observaciones("Perdido").build()
                )
        );
    }
}
