package com.odoo.manager.repo;

import com.odoo.manager.model.AuOXMLFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AuOXMLFileRepo {

    private final JdbcTemplate jdbcTemplate;

    public boolean existsBySha256(String sha256) {
        String sql = "SELECT COUNT(*) FROM au_oxml_files WHERE sha256 = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, sha256);
        return count != null && count > 0;
    }

    public boolean save(AuOXMLFile auOXMLFile) {
        String sql = "INSERT INTO au_oxml_files (fecha, archivo, sha256) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, auOXMLFile.getFecha(), auOXMLFile.getArchivo(), auOXMLFile.getSha256());
        return rowsAffected > 0;
    }
}
