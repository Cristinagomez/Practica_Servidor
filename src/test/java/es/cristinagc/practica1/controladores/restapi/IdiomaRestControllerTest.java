package es.cristinagc.practica1.controladores.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = IdiomaRestController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class)
class IdiomaRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IdiomaService idiomaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAll_shouldReturnListOfIdiomas () throws Exception {
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
                .andExpect(jsonPath("$[0].nombre", Matchers.is("Francés")))
                .andDo(print());
    }

    @Test
    void getAll_shouldReturnInternalServerError () throws Exception {
        Mockito.when(idiomaService.findAll()).thenThrow(RuntimeException.class);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/idiomas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andDo(print());
    }
    @Test
    public void getOne_shouldReturnOneIdioma() throws Exception {
        Idioma idm1 = Idioma.builder().nombre("Francés").build();
        Mockito.when(idiomaService.findById(Datasets.IDM_ID_1))
                .thenReturn(Optional.of(idm1));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/idiomas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", Matchers.is("Francés")))
                .andDo(print());

    }

    @Test
    void getOne_shouldReturnNotfound () throws Exception {
        Mockito.when(idiomaService.findById(Datasets.IDM_ID_1))
                .thenReturn(Optional.ofNullable(null));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/idiomas/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void add_shouldCreateIdioma () throws Exception {
        Idioma idm1 = Idioma.builder().id(1L).nombre("Francés").build();

        when(idiomaService.save(any())).thenReturn(idm1);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/idiomas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(idm1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andDo(print());
    }

    @Test
    void add_shouldReturnInternalServerError () throws Exception {
        Idioma idm1 = Idioma.builder().id(Datasets.IDM_ID_1).nombre("Francés").build();
        when(idiomaService.save(any())).thenThrow(RuntimeException.class);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/idiomas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(idm1)))
                .andExpect(status().isInternalServerError())
                .andDo(print());
    }

    @Test
    void update_shouldUpdateIdioma () throws Exception {
        Idioma idm1 = Idioma.builder().id(Datasets.IDM_ID_1).nombre("Francés").build();
        when(idiomaService.findById(Datasets.IDM_ID_1))
                .thenReturn(Optional.of(idm1));
        when(idiomaService.save(any())).thenReturn(idm1);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/idiomas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(idm1)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void update_shouldReturnNotFound () throws Exception {
        Idioma idm1 = Idioma.builder().id(Datasets.IDM_ID_1).nombre("Francés").build();
        when(idiomaService.findById(Datasets.IDM_ID_1))
                .thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/idiomas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(idm1)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void delete_shouldDeleteIdioma () throws Exception {
        Idioma idm1 = Idioma.builder().id(Datasets.IDM_ID_1).nombre("Francés").build();
        when(idiomaService.findById(Datasets.IDM_ID_1))
                .thenReturn(Optional.of(idm1));
        doNothing().when(idiomaService).deleteById(any());
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/idiomas/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void delete_shouldReturnNotFound () throws Exception {
        when(idiomaService.findById(Datasets.IDM_ID_1))
                .thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/idiomas/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

}