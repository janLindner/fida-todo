package me.lindner.fidatodo.api;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dueDate;
    @NotEmpty
    private String content;
}
