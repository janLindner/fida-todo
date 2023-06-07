package me.lindner.fidatodo.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ToDoEntryResponse {

    @NotNull
    private final UUID id;
    @NotNull
    @JsonFormat(pattern = "dd.MM.YYYY")
    private final LocalDate dueDate;
    @NotEmpty
    private final String content;
    private final boolean completed;
}
