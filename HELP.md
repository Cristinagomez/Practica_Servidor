# Práctica 4 - Sitio web con Servicios web basados en REST
### Cristina Gómez Campos

#### Tématica: Libros
#### Repositorio: https://github.com/Cristinagomez/Practica_Servidor
### **Gestión de usuarios:**

- `admin` 

    **contraseña:** admin
- `user` 

    **contraseña:** user
- `invitado` 

    **contraseña:** invitado

### **Funcionalidades añadidas:**

- `Entidad publicada como api REST: Idioma`
  **En la carpeta resapi dentro del paquete controladores. Test correspondientes, actualmente fallan dos.**
- `DTO: idioma, libro`
  **En el paquete dto**
- `JWT`
  **En el paquete dto y en el paquete seguridad**
- `CORS`
  **Anotación @CrossOrigin(origins = "http://localhost:9001") para permitir ese origen en el método getAll, en la clase IdiomaRestController del paquete resapi dentro de controladores.**
- `Manejo de errores y excepciones`
  **En el paquete error se añaden las clases necesarias.**
- `Swagger`
  **En la clase IdiomaRestController añadiendo anotaciones en los métodos e incluyendo las dependencias correspondientes en el pom.**
- `Test faltantes en la anterior práctica`
  **En CodigoRepositoryTest fallan dos y en IdiomaRepositoryTest fallan uno por violación de una restricción de Integridad Referencial (Could not initialize dataset: datasets/idiomas.yml, datasets/libros.yml, Exception processing table name='LIBRO_GENERO')**


