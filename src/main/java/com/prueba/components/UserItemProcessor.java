package com.prueba.components;

import com.prueba.models.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class UserItemProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(User user) throws Exception {
        user.setNombre(user.getNombre().toUpperCase());

        if (!isValidEmail(user.getEmail())) {
            throw new ValidationException("Invalid email format: " + user.getEmail());
        }

        return user;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}

