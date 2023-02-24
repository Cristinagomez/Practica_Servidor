package es.cristinagc.practica1.repositorios;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import es.cristinagc.practica1.Datasets;
import es.cristinagc.practica1.entidades.Preferencias;
import es.cristinagc.practica1.entidades.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest(showSql = false)
@DBRider
class PreferenciasRepositoryTest {
    @Autowired
    private PreferenciasRepository preferenciasRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @DataSet("datasets/usuarios.yml")
    void should_createPreferencias() {

        Preferencias pref = Preferencias.builder()
                .darkMode(true)
                .idioma("es_ES")
                .build();

        Usuario usuario = usuarioRepository.findById(Datasets.USUARIO_ID_1).orElse(null);
        pref.setUsuario(usuario);
        preferenciasRepository.save(pref);

        assertThat(preferenciasRepository.findById(Datasets.USUARIO_ID_1)).isNotNull();

    }
}