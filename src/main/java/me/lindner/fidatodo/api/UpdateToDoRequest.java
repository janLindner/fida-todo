package me.lindner.fidatodo.api;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateToDoRequest {

    @NotNull
    private LocalDate dueDate;
    @NotEmpty
    private String content;
    @NotNull
    private Boolean completed;
}
