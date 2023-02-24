package es.cristinagc.practica1.repositorios;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import es.cristinagc.practica1.Datasets;
import es.cristinagc.practica1.entidades.Genero;
import es.cristinagc.practica1.entidades.Libro;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@DataJpaTest(showSql = false)
@DBRider
class LibroRepositoryTest {

    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private GeneroRepository generoRepository;

    @Test
    void should_findByTituloContainsIgnoreCase() {

        Libro l = Libro.builder()
                .titulo("Drácula")
                .autor("Bram Stoker")
                .anioPublicacion("1897")
                .anioEdicion("2019")
                .editorial("Debolsillo")
                .disponible(true)
                .observaciones("")
                .build();

        libroRepository.save(l);

        List<Libro> resultados = libroRepository.findByTituloContainsIgnoreCase("drácula");
        int numLibros = 1;
        assertThat(resultados).hasSize(numLibros);
    }
    @Test
    void should_findByTituloStartingWithIgnoreCase() {

        Libro l = Libro.builder()
                .titulo("Drácula")
                .autor("Bram Stoker")
                .anioPublicacion("1897")
                .anioEdicion("2019")
                .editorial("Debolsillo")
                .disponible(true)
                .observaciones("")
                .build();

        libroRepository.save(l);

        List<Libro> resultados = libroRepository.findByTituloStartingWithIgnoreCase("drá");
        int numLibros = 1;
        assertThat(resultados).hasSize(numLibros);
    }

    @Test
    void should_deleteAll() {
        Libro l1 = Libro.builder()
                .titulo("Drácula")
                .autor("Bram Stoker")
                .anioPublicacion("1897")
                .anioEdicion("2019")
                .editorial("Debolsillo")
                .disponible(true)
                .observaciones("")
                .build();

        libroRepository.save(l1);

        Libro l2 = Libro.builder()
                .titulo("El resplandor")
                .autor("Stephen King")
                .anioPublicacion("1977")
                .anioEdicion("2006")
                .editorial("Burlington")
                .disponible(false)
                .observaciones("El ejemplar está dañado")
                .build();

        libroRepository.save(l2);

        libroRepository.deleteAll();

        assertThat(libroRepository.findAll()).isEmpty();
    }

    @Test
    void should_deleteAllInBatch() {
        Libro l1 = Libro.builder()
                .titulo("Drácula")
                .autor("Bram Stoker")
                .anioPublicacion("1897")
                .anioEdicion("2019")
                .editorial("Debolsillo")
                .disponible(true)
                .observaciones("")
                .build();

        libroRepository.save(l1);

        Libro l2 = Libro.builder()
                .titulo("El resplandor")
                .autor("Stephen King")
                .anioPublicacion("1977")
                .anioEdicion("2006")
                .editorial("Burlington")
                .disponible(false)
                .observaciones("El ejemplar está dañado")
                .build();

        libroRepository.save(l2);

        libroRepository.deleteAllInBatch();

        assertThat(libroRepository.findAll()).isEmpty();
    }

    @Test
    @DataSet(value = {"datasets/generos.yml", "datasets/idiomas.yml", "datasets/libros.yml"})
    void should_updateLibro () {

        Libro l1 = libroRepository.findById(Datasets.LIBRO_ID_1).orElse(null);
        Genero g1 = generoRepository.findById(Datasets.GENERO_ID_1).orElse(null);

        l1.getGeneros().add(g1);
        libroRepository.save(l1);

        log.info("antes del assert");
        Optional<Libro> l2 = libroRepository.findById(Datasets.LIBRO_ID_1);
        if (l2.isPresent()) {
            assertThat(l2.get().getGeneros()).hasSize(3);
        }
    }

    @Test
    @DataSet(value = {"datasets/generos.yml"})
    void should_insertLibroAndAddToGenero () {
        Libro l1 = Libro.builder()
                .titulo("Drácula")
                .autor("Bram Stoker")
                .anioPublicacion("1897")
                .anioEdicion("2019")
                .editorial("Debolsillo")
                .disponible(true)
                .observaciones("")
                .build();


        Genero g1 = generoRepository.findById(Datasets.GENERO_ID_1).orElse(null);

        l1.setGeneros(Set.of(g1));
        // Probar sin y conn la siguiente sentencia y ver en el depurador
        //prj1.getEmpleados().add(emp1);
        libroRepository.save(l1);

        Libro l2 = libroRepository.findByTitulo("Drácula");

        if (l2 != null) {
            assertThat(l2.getGeneros()).hasSize(1);
        }

    }
}