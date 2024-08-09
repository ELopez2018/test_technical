package com.prueba.components;


import com.prueba.models.User;
import org.springframework.batch.item.ItemProcessor;

import java.util.regex.Pattern;

public class CsvItemProcessor implements ItemProcessor<User, User> {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    @Override
    public User process(User user) throws Exception {
        // Convertir nombre a mayúsculas
        user.setNombre(user.getNombre().toUpperCase());

        // Validar email
        if (!EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            throw new Exception("Email no válido: " + user.getEmail());
        }

        return user;
    }
}

