package org.example.exceptions;

/**
 * @className: PersonalNotFoundException
 * @date: 21.04.2025
 * @author: Uralbaev Diyorbek
 */

public class PersonalNotFoundException extends RuntimeException {
    public PersonalNotFoundException(String message) {
        super(message);
    }
}
