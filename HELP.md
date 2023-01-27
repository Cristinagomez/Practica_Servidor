# Práctica 3 - Sitio web con Spring Boot, Spring MVC, Thymeleaf y Spring Data JPA
### Cristina Gómez Campos

#### Tématica: Libros
#### Repositorio: https://github.com/Cristinagomez/Practica_Servidor
### **Gestión de usuarios:**

- `admin` edita, añade y elimina.

    **contraseña:** admin
- `user` solo puede editar.

    **contraseña:** user
- `invitado` solo visualiza la lista.

    **contraseña:** invitado

### **Cambios respecto a la práctica anterior:**

- El atributo cantidad desaparece de la entidad Libro.
- Se añadió el atributo código a la entidad Libro.
- El atributo genero ahora es una lista,ya que un libro puede tener varios géneros. Y ahora e suna entidad de tipo class y no un enumerado.
- El atributo idioma deja de ser un enumerado y pasa a ser una entidad de tipo class.
- Nuevas entidades: Codigo, Genero, Idioma, Preferencias, Usuario.
- En el menú de las vistas ya no aparece el contador de visitas.
- El cambio de idioma y el modo oscuro ya no están en la barra de menú, pasan a estar en la pestaña de editar preferencias.

### **Relaciones:**