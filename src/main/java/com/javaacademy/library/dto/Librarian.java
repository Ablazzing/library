package com.javaacademy.library.dto;

import lombok.Data;

/**
 * Библиотекарь
 */
@Data
public class Librarian {
    private String uniqueName;
    private WorkStatus status;

    public void run() {
        System.out.println("Библиотекарь бежит");
    }
}
