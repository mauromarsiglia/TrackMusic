```markdown
# TrackMusic API Project

Este proyecto implementa un conjunto de APIs para la gestión de listas de reproducción y canciones utilizando tecnologías Java, JPA y un servidor Payara. Adicionalmente, se incluye una capa de persistencia con una base de datos en memoria H2 y autenticación mediante JWT.

## Requisitos

- Java 8 o superior
- Maven 3.6.3 o superior
- Payara Server 5.2020.6
- Postman (para pruebas de API)

## Estructura del Proyecto

```
TrackMusic
│ ├── src/
│ │ ├── main/
│ │ │ ├── java/
│ │ │ │ ├── co/com/quipux/trackmusic/
│ │ │ │ │ ├── api/ # Endpoints RESTful
│ │ │ │ │ ├── ejb/ # Facades
│ │ │ │ │ ├── entities/ # Entidades JPA
│ │ │ │ │ ├── services/ # Lógica de negocios y acceso a datos
│ │ │ │ │ ├── utils/ # Utilidades varias
│ │ │ ├── resources/ # Archivos de configuración
│ │ │ ├── webapp/ # Archivos web
│ │ ├── test/ # Pruebas unitarias
│ ├── pom.xml # Configuración de Maven
└── README.md # Este archivo
└── nb-configuration           # Archivo de configuración de Apache Netbeans
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

#### Ver todas las listas de reproducción

- **URL:** `/lists`
- **Método:** `GET`

#### Ver una lista de reproducción específica

- **URL:** `/lists/{listName}`
- **Método:** `GET`

#### Borrar una lista de reproducción

- **URL:** `/lists/{listName}`
- **Método:** `DELETE`

## Autores

- [Mauro Marsiglia] (https://www.linkedin.com/in/mauromarsiglia/)

