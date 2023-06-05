package me.lindner.fidatodo.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ToDoEntry {

    private final UUID id;
    private LocalDate dueDate;
    private String content;
    private boolean completed;
}
