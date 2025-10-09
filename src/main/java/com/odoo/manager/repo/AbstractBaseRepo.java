package com.odoo.manager.repo;

import com.odoo.manager.bean.NullFriendlyBeanPropertySqlParameterSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;

/**
 * Clase base abstracta para repositorios de acceso a datos en el sistema de gestión de Odoo.
 * <p>
 * Proporciona funcionalidad común para operaciones de base de datos mediante Spring JDBC,
 * incluyendo inserciones por lotes, consultas y marcado de registros exportados.
 * Esta clase utiliza el patrón Template Method para definir el comportamiento base
 * mientras permite a las subclases implementar operaciones específicas.
 * </p>
 *
 * @param <T> el tipo de entidad que maneja este repositorio
 * @author Sistema de Gestión Odoo
 * @version 1.0
 * @since 2025-10-08
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractBaseRepo<T> {

    /**
     * Template de JDBC utilizado para ejecutar operaciones de base de datos.
     * <p>
     * Proporciona acceso directo a la base de datos mediante Spring JDBC.
     * Es inyectado automáticamente por el constructor generado por Lombok.
     * </p>
     */
    protected final JdbcTemplate jdbcTemplate;

    /**
     * Inserta un lote de registros en la base de datos utilizando operaciones por lotes.
     * <p>
     * Este método optimiza la inserción de múltiples registros ejecutándolos en una
     * sola operación batch. Utiliza {@link NullFriendlyBeanPropertySqlParameterSource}
     * para manejar valores nulos de manera segura durante la inserción.
     * </p>
     * <p>
     * El método registra en el log la cantidad de filas insertadas exitosamente.
     * Si la lista es nula o vacía, no se ejecuta ninguna operación.
     * </p>
     *
     * @param list   la lista de entidades a insertar. Puede ser nula o vacía,
     *               en cuyo caso no se realiza ninguna operación
     * @param sql    la sentencia SQL parametrizada para la inserción.
     *               Debe contener un marcador de formato para el esquema (ej: "INSERT INTO %s.table...")
     * @param schema el nombre del esquema de base de datos donde se insertarán los registros
     * @throws org.springframework.dao.DataAccessException si ocurre un error durante la inserción
     */
    public int onInsertAsBatch(List<T> list, String sql, String schema) {
        if (null != list && !list.isEmpty()) {
            sql = String.format(sql, schema);
            NamedParameterJdbcTemplate nJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
            SqlParameterSource[] batch = list.stream()
                    .map(NullFriendlyBeanPropertySqlParameterSource::new)
                    .toArray(SqlParameterSource[]::new);
            nJdbcTemplate.batchUpdate(sql, batch);
            return batch.length;
        }
        return 0;
    }

    /**
     * Recupera todos los registros que han sido marcados como exportados.
     * <p>
     * Este método debe ser implementado por las subclases para retornar
     * únicamente aquellos registros cuyo campo de exportación no sea nulo,
     * indicando que ya han sido procesados o exportados.
     * </p>
     *
     * @return una lista de entidades que tienen marcada la exportación.
     * Nunca debe retornar null, usar una lista vacía si no hay resultados
     */
    public abstract List<T> findAllByExportedIsNotNull();

    /**
     * Marca un grupo de registros como exportados en la base de datos.
     * <p>
     * Este método debe ser implementado por las subclases para actualizar
     * el estado de exportación de los registros proporcionados, típicamente
     * estableciendo una fecha/hora de exportación o un flag booleano.
     * </p>
     *
     * @param group la lista de entidades que se marcarán como exportadas.
     *              No debe ser nula
     * @throws org.springframework.dao.DataAccessException si ocurre un error durante la actualización
     */
    public abstract void markGroupAsExported(List<T> group);

    /**
     * Guarda un lote de registros en el esquema especificado de la base de datos.
     * <p>
     * Este método debe ser implementado por las subclases para persistir
     * múltiples entidades en una sola operación, optimizando el rendimiento
     * mediante inserciones por lotes.
     * </p>
     *
     * @param list   la lista de entidades a guardar. No debe ser nula
     * @param schema el nombre del esquema de base de datos donde se guardarán los registros
     * @throws org.springframework.dao.DataAccessException si ocurre un error durante el guardado
     */
    public abstract void saveBatch(List<T> list, String schema);
}
