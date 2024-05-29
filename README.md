```markdown
# TrackMusic API Project

Este proyecto implementa un conjunto de APIs para la gestión de listas de reproducción y canciones utilizando tecnologías Java, JPA y un servidor Payara. Adicionalmente, se incluye una capa de persistencia con una base de datos en memoria H2 y autenticación mediante JWT.

## Requisitos

- Java 8 o superior
- Maven 3.6.3 o superior
- Payara Server 5.2020.6
- Postman (para pruebas de API)

```

## Configuración y Ejecución

### Backend

1. Clona el repositorio:
   ```sh
   https://github.com/mauromarsiglia/TrackMusic.git
   cd TrackMusic
   ```

2. Configura la base de datos H2:
   El archivo de configuración `persistence.xml` ya está configurado para utilizar una base de datos en memoria H2.

3. Compila y empaqueta la aplicación:
   ```sh
   mvn clean package
   ```

4. Despliega la aplicación en Payara:
   - Copia el archivo `target/TrackMusicServices.war` a la carpeta `deployments` de tu servidor Payara.
   - Asegúrate de que el servidor Payara esté en ejecución.

5. Accede a la aplicación:
   Abre tu navegador y ve a `http://localhost:8080/TrackMusic`.

### Herramientas de Prueba

Puedes usar Postman para probar los endpoints de la API:

- Importa la colección de Postman adjunta para tener acceso a todas las rutas configuradas.
- Asegúrate de tener un token JWT válido para autenticar tus solicitudes.

## Documentación de la API

### Endpoints

#### Añadir una nueva lista de reproducción

- **URL:** `/lists`
- **Método:** `POST`
- **Descripción:** Añadir una nueva lista de reproducción.
- **Request Body:**
  ```json
  {
    "nombre": "Lista 1",
    "descripcion": "Lista de canciones de Spotify",
    "canciones": [
      {
        "titulo": "Song 1",
        "artista": "Artist 1",
        "album": "Album 1",
        "anno": "2021",
        "genero": "Pop"
      }
    ]
  }
  ```
- **Responses:**
  - **201 Created:** Lista creada exitosamente.
  - **400 Bad Request:** Nombre de la lista no válido.

#### Ver todas las listas de reproducción

- **URL:** `/lists`
- **Método:** `GET`
- **Descripción:** Ver todas las listas de reproducción existentes.
- **Responses:**
  - **200 OK:** Listas de reproducción devueltas exitosamente.

#### Ver una lista de reproducción específica

- **URL:** `/lists/{listName}`
- **Método:** `GET`
- **Descripción:** Ver descripción de una lista de reproducción seleccionada.
- **Responses:**
  - **200 OK:** Lista de reproducción devuelta exitosamente.
  - **404 Not Found:** La lista de reproducción no existe.

#### Borrar una lista de reproducción

- **URL:** `/lists/{listName}`
- **Método:** `DELETE`
- **Descripción:** Borrar una lista de reproducción.
- **Responses:**
  - **204 No Content:** Lista de reproducción borrada exitosamente.
  - **404 Not Found:** La lista de reproducción no existe.

#### Iniciar sesión

- **URL:** `/login`
- **Método:** `POST`
- **Descripción:** Autenticar un usuario y devolver un token JWT.
- **Request Body:**
  ```json
  {
    "email": "mauro.marsiglia@gmail.com",
    "password": "D3v3l0p3rS3l3cc10n4d0"
  }
  ```
- **Responses:**
  - **200 OK:** Autenticación exitosa. Devuelve un token JWT.
  - **400 Bad Request:** Credenciales inválidas.
  - **401 Unauthorized:** Usuario no encontrado.

#### Obtener géneros musicales de Spotify

- **URL:** `/spotify/genres`
- **Método:** `GET`
- **Descripción:** Obtener una lista de géneros musicales desde Spotify.
- **Responses:**
  - **200 OK:** Géneros musicales devueltos exitosamente.
  - **500 Internal Server Error:** Error al comunicarse con el servicio de Spotify.

## Ejemplos de Uso de los Endpoints

### Añadir una nueva lista de reproducción

**Request:**

```sh
curl -X POST "http://ec2-34-201-187-69.compute-1.amazonaws.com/TrackMusic/lists" -H "Content-Type: application/json" -d '{
  "nombre": "Lista 1",
  "descripcion": "Lista de canciones de Spotify",
  "canciones": [
    {
      "titulo": "Song 1",
      "artista": "Artist 1",
      "album": "Album 1",
      "anno": "2021",
      "genero": "Pop"
    }
  ]
}'
```

**Response:**

```json
{
  "status": "201 Created",
  "message": "Lista creada exitosamente."
}
```

### Ver todas las listas de reproducción

**Request:**

```sh
curl -X GET "http://ec2-34-201-187-69.compute-1.amazonaws.com/TrackMusic/lists"
```

**Response:**

```json
[
  {
    "nombre": "Lista 1",
    "descripcion": "Lista de canciones de Spotify",
    "canciones": [
      {
        "titulo": "Song 1",
        "artista": "Artist 1",
        "album": "Album 1",
        "anno": "2021",
        "genero": "Pop"
      }
    ]
  }
]
```

### Iniciar sesión

**Request:**

```sh
curl -X POST "http://ec2-34-201-187-69.compute-1.amazonaws.com/TrackMusic/login" -H "Content-Type: application/json" -d '{
  "email": "mauro.marsiglia@gmail.com",
  "password": "D3v3l0p3rS3l3cc10n4d0"
}'
```

**Response:**

```json
{
  "status": "200 OK",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### Obtener géneros musicales de Spotify

**Request:**

```sh
curl -X GET "http://ec2-34-201-187-69.compute-1.amazonaws.com/TrackMusic/spotify/genres"
```

**Response:**

```json
[
  "Pop",
  "Rock",
  "Jazz",
  "Classical",
  ...
]
```

## Autores

- [Mauro Marsiglia] (https://www.linkedin.com/in/mauromarsiglia/)

