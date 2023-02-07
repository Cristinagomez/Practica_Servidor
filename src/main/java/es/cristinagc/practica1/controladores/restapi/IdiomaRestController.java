package es.cristinagc.practica1.controladores.restapi;

import es.cristinagc.practica1.entidades.Idioma;
import es.cristinagc.practica1.servicios.IdiomaService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Data
@RestController
@RequestMapping("/api/idiomas")
public class IdiomaRestController {

    private final IdiomaService idiomaService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Idioma>> getAll() {
        try {
            List<Idioma> idiomas = idiomaService.findAll();
            return new ResponseEntity<>(idiomas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping({ "/{id}" })
    public ResponseEntity<Idioma> getOne(@PathVariable("id") Long id) {
        Optional<Idioma> idioma = idiomaService.findById(id);
        return idioma.map(i -> new ResponseEntity<>(i, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Map<String, Object>> add(@Valid @RequestBody Idioma idioma) {
        try {
            Long id = idiomaService.save(idioma).getId();
            return new ResponseEntity<>(Collections.singletonMap("id", id), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PutMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody @Valid Idioma idioma,
                                                      @PathVariable("id") Long id) {
        try {
            Optional<Idioma> i = idiomaService.findById(id);
            if (i.isPresent()) {
                idiomaService.save(idioma);
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") Long id) {
        try {
            Optional<Idioma> idioma = idiomaService.findById(id);
            if (idioma.isPresent()) {
                idiomaService.deleteById(id);
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
