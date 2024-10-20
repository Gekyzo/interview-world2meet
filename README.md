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

