package es.cristinagc.practica1.repositorios;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import es.cristinagc.practica1.Datasets;
import es.cristinagc.practica1.entidades.Idioma;
import es.cristinagc.practica1.entidades.Libro;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest(showSql = false)
@DBRider
class IdiomaRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IdiomaRepository idiomaRepository;

    @Test
    void should_find_no_idiomas_if_repository_is_empty() {
        Iterable idiomas = idiomaRepository.findAll();

        assertThat(idiomas).isEmpty();
    }

    @Test
    void should_persist() {
        // Transient
        Idioma idioma = Idioma.builder()
                .nombre("Italiano")
                .build();
        log.info("Idioma transient {}", idioma);

        // Persist
        entityManager.persist(idioma);
        log.info("Idioma después de persist {}", idioma);
        assertThat(idioma.getId()).isNotNull();

        // getId
        Idioma idiomaInContext = entityManager.find(Idioma.class, idioma.getId());
        log.info("Idioma in context {}", idiomaInContext);
        assertThat(idiomaInContext).isEqualTo(idioma);

        // Flush
        idioma.setNombre("entity is managed");
        entityManager.flush();
        log.info("Idioma después de flush {}", idioma);
    }

    @Test
    @DataSet("datasets/idiomas.yml")
    void should_detach() {
        Idioma idmDetached = entityManager.find(Idioma.class, Datasets.IDM_ID_1); // Informática
        idmDetached.setNombre("detached!!");
        entityManager.detach(idmDetached);
        entityManager.flush();
        log.info("Idioma después de detach y flush {}", idmDetached);
        Idioma idioma = entityManager.find(Idioma.class, Datasets.IDM_ID_1);
        log.info("Idioma después de find {}", idioma);
        assertThat(idmDetached).isNotEqualTo(idioma);
        assertThat(idioma.getNombre()).isEqualTo("Italiano");
    }

    @Test
    @DataSet("datasets/idiomas.yml")
    void should_remove() {
        Idioma idmRemove = entityManager.find(Idioma.class, Datasets.IDM_ID_1);
        entityManager.remove(idmRemove);
        log.info("Idioma después de remove {}", idmRemove);
        assertThat(entityManager.find(Idioma.class, Datasets.IDM_ID_1));
        entityManager.flush();
    }

    @Test
    @DataSet("datasets/idiomas.yml")
    void should_merge_detached() {
        Idioma idmDetached = entityManager.find(Idioma.class, Datasets.IDM_ID_1);
        entityManager.detach(idmDetached);
        log.info("Idioma detached {}", idmDetached);

        idmDetached.setNombre("merged!!");
        Idioma idmManaged = entityManager.merge(idmDetached);
        log.info("Idioma detached después de merge {} {}", idmDetached.hashCode(), idmDetached);
        log.info("Idioma managed después de merge {} {} ", idmManaged.hashCode(), idmManaged);

        assertThat(idmManaged).isNotEqualTo(idmDetached);
        assertThat(idmManaged.getNombre()).isEqualTo(idmDetached.getNombre());
        entityManager.flush();
    }


    @Test
    @DataSet(value = {"datasets/idiomas.yml", "datasets/libros.yml"})
    void should_findIdiomasBidirectional () {
        Idioma idioma = idiomaRepository.findById(Datasets.IDM_ID_1).orElse(null);
        log.info("antes del assert");
        assertThat(idioma.getLibros()).hasSize(2);

    }


    @Test
    void should_addIdiomaAndNewLibro () {
        Idioma idioma = Idioma.builder()
                .nombre("Italiano")
                .libros(new ArrayList<>())
                .build();
        Libro libro = Libro.builder()
                .titulo("El fantasma de Canterville")
                .build();
        idioma.addLibro(libro);
        idiomaRepository.save(idioma);
        Idioma idmSaved = idiomaRepository.findByNombre("Italiano");
        assertThat(idmSaved.getLibros()).hasSize(1);

    }
}