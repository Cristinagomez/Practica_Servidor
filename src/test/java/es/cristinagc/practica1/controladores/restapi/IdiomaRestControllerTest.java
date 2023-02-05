package es.cristinagc.practica1.controladores.restapi;

import es.cristinagc.practica1.entidades.Idioma;
import es.cristinagc.practica1.repositorios.Datasets;
import es.cristinagc.practica1.servicios.IdiomaService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = IdiomaRestController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class)
class IdiomaRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IdiomaService idiomaService;

    @Test
    public void getAllIdiomas_success () throws Exception {
        Idioma idm1 = Idioma.builder().nombre("Francés").build();
        Idioma idm2 = Idioma.builder().nombre("Italiano").build();
        Idioma idm3 = Idioma.builder().nombre("Español").build();

        List<Idioma> idiomas = Arrays.asList(idm1,idm2,idm3);

        Mockito.when(idiomaService.findAll()).thenReturn(idiomas);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/idiomas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].nombre", Matchers.is("Francés")));
    }

    @Test
    void getAllIdiomas_exception () throws Exception {
        Mockito.when(idiomaService.findAll()).thenThrow(RuntimeException.class);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/idiomas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
    @Test
    public void getOneIdioma_success() throws Exception {
        Idioma idm1 = Idioma.builder().nombre("Francés").build();
        Mockito.when(idiomaService.findById(Datasets.IDM_ID_1))
                .thenReturn(Optional.of(idm1));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/idiomas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", Matchers.is("Francés")));

    }

    @Test
    void getOneIdioma_notfound () throws Exception {
        Mockito.when(idiomaService.findById(Datasets.IDM_ID_1))
                .thenReturn(Optional.ofNullable(null));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/idiomas/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}