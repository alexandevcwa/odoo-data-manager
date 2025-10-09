package com.odoo.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Clase de configuración para Jackson XML Mapper en el sistema de gestión de Odoo.
 * <p>
 * Esta clase configura el comportamiento de serialización y deserialización XML
 * utilizando Jackson, especialmente para el manejo correcto de tipos de fecha y hora
 * de Java 8 (JSR-310) como {@link java.time.LocalDate}, {@link java.time.LocalDateTime},
 * y {@link java.time.ZonedDateTime}.
 * </p>
 * <p>
 * La configuración aplica las siguientes características:
 * <ul>
 *   <li>Soporte para tipos de fecha/hora de Java 8 mediante {@link JavaTimeModule}</li>
 *   <li>Serialización de fechas en formato ISO-8601 en lugar de timestamps numéricos</li>
 *   <li>Mapper XML configurado globalmente para toda la aplicación Spring</li>
 * </ul>
 * </p>
 *
 * @author Sistema de Gestión Odoo
 * @version 1.0
 * @since 2025-10-08
 * @see XmlMapper
 * @see JavaTimeModule
 */
@Configuration
public class JacksonConf {

    /**
     * Configura y proporciona un bean de {@link XmlMapper} personalizado para la aplicación.
     * <p>
     * Este método crea una instancia de XmlMapper con las siguientes configuraciones:
     * </p>
     * <ul>
     *   <li><strong>JavaTimeModule:</strong> Habilita el soporte para tipos de fecha/hora de Java 8
     *       (LocalDate, LocalDateTime, ZonedDateTime, Instant, etc.)</li>
     *   <li><strong>WRITE_DATES_AS_TIMESTAMPS deshabilitado:</strong> Configura la serialización
     *       de fechas en formato ISO-8601 legible (ej: "2025-10-08T10:30:00") en lugar de
     *       timestamps numéricos (ej: 1728385800000)</li>
     * </ul>
     * <p>
     * El XmlMapper resultante será inyectado automáticamente por Spring en todos los
     * componentes que requieran serialización/deserialización XML, como controladores REST,
     * servicios de importación/exportación de datos, etc.
     * </p>
     * <p>
     * <strong>Ejemplo de uso:</strong>
     * <pre>{@code
     * @Autowired
     * private XmlMapper xmlMapper;
     *
     * public String serialize(OdooData data) throws JsonProcessingException {
     *     return xmlMapper.writeValueAsString(data);
     * }
     * }</pre>
     * </p>
     *
     * @return una instancia configurada de {@link XmlMapper} lista para serialización
     *         y deserialización XML con soporte completo para tipos de Java 8
     */
    @Bean
    XmlMapper objectMapper() {
        return XmlMapper.xmlBuilder().addModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();
    }

}
