package com.prueba.components;

import com.prueba.models.User;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PersonaItemWriter implements ItemWriter<User> {

    private final JdbcTemplate jdbcTemplate;

    public PersonaItemWriter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void write(Chunk<? extends User> items) throws Exception {
        for (User persona : items) {
            jdbcTemplate.update("INSERT INTO sser (id, nombre, email) VALUES (?, ?, ?)",
                    persona.getId(), persona.getNombre(), persona.getEmail());
        }
    }
}