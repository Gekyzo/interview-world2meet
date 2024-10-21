## Requisitos técnicos

- Java 23
- Maven > 3.9
- Docker

## Instalación

#### 1. Mediante Docker

El servicio se puede arrancar automáticamente utilizando la imagen de docker que se encuentra en la raíz, `composer.yaml`. Esta imagen genera tanto la imagen de la base de datos postgre, como su propia imagen de spring, y despliega los contenedores en los puertos por defecto.

#### 2. Mediante terminal

Ejecutar el comando

````bash
mvn spring-boot:run
````

#### 3. Manualmente

En caso de abrir el servicio en un IDE, se puede arrancar mediante los iconos de **RUN**.

Al iniciar el servicio por primera vez, se generará la imagen de la base de datos automáticamente. Esta imagen se detendrá a la vez que detenemos el servicio.

## Variables de entorno

| Variable    | Descripción                             | Valor por defecto |
| ----------- | --------------------------------------- | ----------------- |
| SERVER_PORT | Puerto HTTP del servicio                | `8080`            |
| DB_HOST     | URL de la base de datos                 | `localhost`       |
| DB_PORT     | Puerto de la base de datos              | `5432`            |
| DB_NAME     | Nombre de la base de datos              | `private`         |
| DB_USER     | Usuario de acceso a la base de datos    | `postgres`        |
| DB_PASS     | Contraseña de acceso a la base de datos | `secret`          |

## OpenAPI

Rutas de acceso a los swagger del servicio:

|                |                                      |
| -------------- | ------------------------------------ |
| Formato web UI | `<SERVER_URL>/swagger-ui/index.html` |
| Formato JSON   | `<SERVER_URL>/v3/api-docs`           |
| Formato YAML   | `<SERVER_URL>/v3/api-docs.yaml`      |

