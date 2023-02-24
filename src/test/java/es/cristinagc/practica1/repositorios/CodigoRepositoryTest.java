package es.cristinagc.practica1.repositorios;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import es.cristinagc.practica1.Datasets;
import es.cristinagc.practica1.entidades.Codigo;
import es.cristinagc.practica1.entidades.Libro;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest(showSql = false)
@DBRider
class CodigoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;


    @Test
    @DataSet(value = {"datasets/codigos.yml", "datasets/libros.yml"})
    void should_updateCodigoUnidirectional() {
        Object DataSets;
        Codigo codigo = entityManager.find(Codigo.class, Datasets.CODIGO_ID_1);
        codigo.setLibro(entityManager.getEntityManager().getReference(Libro.class, Datasets.LIBRO_ID_1));
        entityManager.flush();
        entityManager.detach(codigo);

        assertThat(entityManager.find(Codigo.class, Datasets.CODIGO_ID_1).getLibro())
                .isNotNull();
    }

    @Test
    @DataSet(value = {"datasets/codigos.yml", "datasets/libros.yml"})
    void should_updateCodigoBidirectional() {
        Libro libro = entityManager.find(Libro.class, Datasets.LIBRO_ID_2);
        libro.setCodigo(entityManager.find(Codigo.class, Datasets.CODIGO_ID_1));
        libro.getCodigo().setLibro(libro);

        entityManager.flush();
        entityManager.detach(libro);

        assertThat(entityManager.find(Libro.class, Datasets.LIBRO_ID_2).getCodigo())
                .isNotNull();
    }


}