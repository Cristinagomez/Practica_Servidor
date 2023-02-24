package es.cristinagc.practica1.dto;

import es.cristinagc.practica1.dto.jwt.SignupRequest;
import es.cristinagc.practica1.dto.jwt.SignupResponse;
import es.cristinagc.practica1.entidades.Idioma;
import es.cristinagc.practica1.entidades.Libro;
import es.cristinagc.practica1.entidades.Usuario;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface JpaMapper {


    IdiomaDto toDto(Idioma entity);

    Idioma toEntity(IdiomaDto dto);

    List<IdiomaDto> toDtoList(List<Idioma> list);

    List<Idioma> toEntityList(List<IdiomaDto> list);

    @Mapping(target = "idioma.libros", ignore = true)
    LibroDto toDto(Libro entity);

    Libro toEntity(LibroDto dto);

    Usuario toEntity(SignupRequest dto);
    SignupResponse toDto(Usuario entity);
}
