package es.cristinagc.practica1.controladores.restapi;

import es.cristinagc.practica1.dto.IdiomaDto;
import es.cristinagc.practica1.dto.JpaMapper;
import es.cristinagc.practica1.entidades.Idioma;
import es.cristinagc.practica1.error.IdiomaNotFoundException;
import es.cristinagc.practica1.servicios.IdiomaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Data
@RestController
@RequestMapping("/api/idiomas")
public class IdiomaRestController {

    private final IdiomaService idiomaService;

    private final JpaMapper mapper;

    @CrossOrigin(origins = "http://localhost:9001")
    @Operation(summary="Obtener todos los idiomas")
    @ApiResponses(value= {
            @ApiResponse(responseCode="200", description="OK", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Idioma.class)) }),
            @ApiResponse(responseCode="404", description="Not Found", content = @Content),
            @ApiResponse(responseCode="500", description="Internal Server Error", content = @Content)
    })
    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAll() {
        List<Idioma> idiomas = idiomaService.findAll();
        if (idiomas.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay idiomas registrados");
        } else {
            return ResponseEntity.ok(mapper.toDtoList(idiomas));
        }
    }

    @Operation(summary="Obtener un idioma por su ID")
    @ApiResponses(value= {
            @ApiResponse(responseCode="200", description="OK", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Idioma.class)) }),
            @ApiResponse(responseCode="404", description="Not Found", content = @Content),
            @ApiResponse(responseCode="500", description="Internal Server Error", content = @Content)
    })
    @GetMapping({ "/{id}" })
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public IdiomaDto getOne(@PathVariable("id") Long id) {
        Optional<IdiomaDto> idiomaDto = Optional.ofNullable(mapper.toDto(idiomaService.findById(id).orElse(null)));
        return idiomaDto.orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No se puede encontrar el idioma con el ID: " + id));
    }

    @Operation(summary="Añadir un idioma")
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> add(@Valid @RequestBody IdiomaDto idiomaDto) {
        Idioma idioma = mapper.toEntity(idiomaDto);
        Long id = idiomaService.save(idioma).getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("id", id));

    }

    @Operation(summary="Actualizar un idioma mediante su id")
    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public IdiomaDto update(@RequestBody @Valid IdiomaDto idiomaDto,
                                                      @PathVariable("id") Long id) {
        Optional<Idioma> idioma = idiomaService.findById(id);
        if (idioma.isPresent()) {
            return mapper.toDto(idiomaService.save(mapper.toEntity(idiomaDto)));
        } else {
            throw new IdiomaNotFoundException(id);
        }
    }

    @Operation(summary="Borrar un idioma mediante su id")
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Idioma idioma = idiomaService.findById(id)
                .orElseThrow(() -> new IdiomaNotFoundException(id));
        idiomaService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
