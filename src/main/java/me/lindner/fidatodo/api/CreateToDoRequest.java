package me.lindner.fidatodo.api;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateToDoRequest {

    private UUID id;
    @NotNull
    private LocalDate dueDate;
    @NotEmpty
    private String content;
}
