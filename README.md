# Odoo Data Manager

## Breve descripción

`odoo-data-manager` es una pequeña utilidad Java (Spring Boot) para extraer conjuntos de datos desde tablas de exportación de Odoo y guardarlos en un archivo OXML (XML con estructura del modelo). El proyecto lee registros marcados como "no exportados", serializa los datos a un archivo usando Jackson `XmlMapper` y luego marca los registros como exportados en la base de datos.

## Instalación

Ejecuta ordenadamente los scrips SQL en tu instancia de base de datos odoo ubicados en la carpeta `migrations`.

Luego ejecutar todos los archivos SQL en la instancia, es requerido insertar el código de instancia, nombre del schema y el código de compañia configurada en odoo.

```sql
insert into odoo_schema_exp (odoo_instance_id, odoo_schema, odoo_company_id) values (1,'ODOO_1',1);
```

El nombre de esquema debe de ser igual al configurado en el centro de datos de respaldo para que los datos se puedan importar en el schema correcto. Si se tienen 2 instancias de odoo, en el centro de datos debe de existir dos esquemas, uno para cada instancia para separa sus datos.

## Principales responsabilidades

- Consultar registros pendientes de exportación (repositorios en `src/main/java/com/odoo/manager/repo`).
- Construir un objeto agregador `OdooData` con listas de entidades (`model`).
- Serializar `OdooData` a un archivo `.oxml` usando `XmlMapper`.
- Confirmar/actualizar los registros como exportados (actualizaciones en lote con `JdbcTemplate`).

## Estructura del proyecto

- `src/main/java/com/odoo/manager/service/OdooExportServiceImpl.java` — Lógica principal de exportación.
- `src/main/java/com/odoo/manager/repo/*` — Repositorios JDBC para leer y marcar registros.
- `src/main/java/com/odoo/manager/model/*` — POJOs que representan las entidades a exportar.
- `src/main/resources/application.yaml` — Configuración de Spring (datasource, etc.).

## Cómo construir y ejecutar

Usando el wrapper de Maven incluido:

```bash
./mvnw clean package -DskipTests -Dspring.datasource.url=jdbc:postgresql://localhost:5432/odoo -Dspring.datasource.username=youruser -Dspring.datasource.password=yourpass


# Ejecutar la app (ejemplo pasando properties de conexión y el comando shell)

java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/odoo -Dspring.datasource.username=user -Dspring.datasource.password=pass -jar odoo-data-manager-0.0.1-SNAPSHOT.jar odoo-export --path {/your/path/} --instance {INSTANCE_ID}
```

## Uso con Spring Shell

Este proyecto también expone un comando de Spring Shell para lanzar la exportación desde una consola interactiva o desde línea de comandos.

Ejemplo de uso:

```bash
odoo-export --path /ruta/a/salida
```

Notas prácticas:

- El comando de Spring Shell está definido en el componente de shell (por ejemplo `OdooExportShell`).
- Cuando uses Spring Shell, el método público anotado con `@ShellMethod` es invocado por el contenedor de Spring; por tanto, puedes anotar ese método con `@Transactional` para que la operación de marcado/confirmación se ejecute en una sola transacción.
- Alternativamente, anota el método público `export` en `OdooExportServiceImpl` con `@Transactional` y deja que el `@ShellMethod` invoque ese servicio; esta opción mantiene la separación de responsabilidades (shell UI vs. lógica de negocio).

O alternativamente, usar `spring-boot:run` y pasar el argumento:

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments="/ruta/a/salida"
```

## Configuración

Edite `src/main/resources/application.yaml` para ajustar la conexión a la base de datos (URL, usuario, contraseña) y cualquier otra propiedad de Spring.

## Notas sobre transacciones

- Las operaciones de marcado (marcar como exportado) se realizan mediante `JdbcTemplate.batchUpdate` en los repositorios.
- Para asegurar que todas las actualizaciones se ejecuten en una sola transacción y que, ante un error, se haga rollback de todas ellas, debe utilizarse `@Transactional` en un método público que sea invocado desde fuera del mismo bean (por la infraestructura de proxies de Spring).

Recomendaciones prácticas:

- Anotar el método público que inicia el flujo de exportación (por ejemplo `export` en `OdooExportServiceImpl`) con `@Transactional` si desea que la escritura de confirmación sea atómica.
- Evitar depender de transacciones en métodos privados o en llamadas internas del mismo bean (no serán interceptadas por el proxy de Spring).

## Ejemplo rápido

1. Construir el proyecto.
2. Ejecutar la aplicación pasando la carpeta destino donde se guardará el archivo OXML.
3. Revisar logs para confirmar que el archivo fue creado y que las filas han sido marcadas como exportadas.

## Pruebas

Ejecute las pruebas con:

```bash
./mvnw test
```

## Problemas comunes

- Ruta inválida: la app valida que la ruta sea un directorio y use `/` como separador.
- `@Transactional` no funciona en métodos privados: mover la anotación a un método público o a otro servicio.

## Contacto y contribuciones

Pull requests y issues son bienvenidos. Mantenga cambios pequeños y documentados.

---

Archivo generado automáticamente: descripción breve del proyecto y notas operativas.
