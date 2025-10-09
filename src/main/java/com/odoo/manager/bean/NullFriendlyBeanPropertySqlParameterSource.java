package com.odoo.manager.bean;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

/**
 * Extensión de {@link BeanPropertySqlParameterSource} que maneja valores nulos de manera amigable
 * para operaciones SQL.
 * <p>
 * Esta clase extiende el comportamiento estándar de Spring JDBC para tratar cadenas vacías como
 * valores nulos en las operaciones de base de datos, evitando errores al insertar strings vacíos
 * en columnas que esperan null. Además, garantiza que siempre se considere que un parámetro
 * tiene valor, delegando el manejo de nulos a la lógica de conversión.
 * </p>
 * <p>
 * <strong>Casos de uso:</strong>
 * <ul>
 *   <li>Inserción de datos desde beans donde las propiedades String vacías deben ser null en BD</li>
 *   <li>Operaciones batch donde se necesita consistencia en el manejo de valores vacíos</li>
 *   <li>Migración de datos donde las cadenas vacías deben normalizarse a null</li>
 * </ul>
 * </p>
 *
 * @author Sistema de Gestión Odoo
 * @version 1.0
 * @since 2025-10-08
 * @see BeanPropertySqlParameterSource
 */
public class NullFriendlyBeanPropertySqlParameterSource extends BeanPropertySqlParameterSource {

    /**
     * Construye una nueva instancia de NullFriendlyBeanPropertySqlParameterSource.
     * <p>
     * Crea un proveedor de parámetros SQL basado en las propiedades del objeto especificado,
     * aplicando el comportamiento de conversión amigable con valores nulos.
     * </p>
     *
     * @param object el objeto bean cuyas propiedades serán utilizadas como parámetros SQL.
     *               No debe ser nulo
     * @throws IllegalArgumentException si el objeto proporcionado es nulo
     */
    public NullFriendlyBeanPropertySqlParameterSource(Object object) {
        super(object);
    }

    /**
     * Obtiene el valor de un parámetro específico, convirtiendo cadenas vacías a null.
     * <p>
     * Este método sobrescribe el comportamiento predeterminado para aplicar la siguiente lógica:
     * <ul>
     *   <li>Si el valor es un String vacío (""), retorna null</li>
     *   <li>Para cualquier otro tipo o valor, retorna el valor original</li>
     * </ul>
     * Esta conversión es útil para bases de datos que distinguen entre null y cadena vacía,
     * normalizando los datos antes de la inserción.
     * </p>
     *
     * @param paramName el nombre del parámetro cuyo valor se desea obtener
     * @return el valor del parámetro, con cadenas vacías convertidas a null,
     *         o el valor original para otros tipos
     * @throws IllegalArgumentException si el nombre del parámetro no corresponde a ninguna
     *                                  propiedad válida del bean
     */
    @Override
    public Object getValue(String paramName) throws IllegalArgumentException {
        Object value = super.getValue(paramName);
        if (value instanceof String) {
            String strValue = (String) value;
            if (strValue.isEmpty()) {
                return null;
            }
        }
        return value;
    }

    /**
     * Indica si un parámetro tiene un valor disponible.
     * <p>
     * Este método sobrescribe el comportamiento predeterminado para retornar siempre {@code true},
     * garantizando que todos los parámetros sean considerados en las operaciones SQL.
     * La conversión de valores vacíos a null se maneja en {@link #getValue(String)},
     * permitiendo que el controlador JDBC determine el tratamiento final del valor.
     * </p>
     * <p>
     * <strong>Nota:</strong> Este comportamiento evita que Spring JDBC omita parámetros que
     * podrían tener valores nulos o vacíos, asegurando que todas las columnas sean incluidas
     * en las sentencias SQL preparadas.
     * </p>
     *
     * @param paramName el nombre del parámetro a verificar (no utilizado en esta implementación)
     * @return siempre {@code true}, indicando que el parámetro tiene un valor disponible
     */
    @Override
    public boolean hasValue(String paramName) {
        return true;
    }
}
