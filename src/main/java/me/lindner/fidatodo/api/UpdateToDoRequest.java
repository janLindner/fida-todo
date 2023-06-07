package me.lindner.fidatodo.api;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dueDate;
    @NotEmpty
    private String content;
    @NotNull
    private Boolean completed;
}
