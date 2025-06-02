package com.ofpo.GestionnaireFormation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/database")
public class DatabaseViewController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/foreign-key-issues")
    public List<Map<String, Object>> getForeignKeyIssues() {
        return jdbcTemplate.queryForList("SELECT * FROM vw_foreign_key_issues");
    }

    @GetMapping("/table-relationships")
    public List<Map<String, Object>> getTableRelationships() {
        return jdbcTemplate.queryForList("SELECT * FROM vw_table_relationships");
    }
} 