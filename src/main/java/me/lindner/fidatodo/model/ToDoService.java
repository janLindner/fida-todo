package me.lindner.fidatodo.model;

import jakarta.enterprise.context.ApplicationScoped;
import me.lindner.fidatodo.api.CreateToDoRequest;
import me.lindner.fidatodo.api.UpdateToDoRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * The {@link ToDoService} is an {@link ApplicationScoped} service that provides an interface and local storage for
 * {@link ToDoEntry To-Do entries}
 */
@ApplicationScoped
public class ToDoService {

    private final List<ToDoEntry> entries = new ArrayList<>();

    /**
     * Determines all currently existing {@link ToDoEntry entries}. The list contains both completed and unfinished
     * {@link ToDoEntry entries}.
     *
     * @return All currently existing {@link ToDoEntry entries} as an unmodifiable copied list.
     */
    public List<ToDoEntry> getEntries() {
        return entries.stream().toList();
    }

    /**
     * Creates a new {@link ToDoEntry entry}. In case no {@link UUID unique identifier} is given. A new random
     * {@link UUID unique identifier} will be created. The created {@link ToDoEntry entry} will be stored locally during
     * the runtime of the application.
     *
     * @param request The {@link CreateToDoRequest request} containing all data that should be considered for the
     *                creation of the {@link ToDoEntry entry}.
     *
     * @return The created {@link ToDoEntry}.
     */
    public ToDoEntry createEntry(final CreateToDoRequest request) {
        final ToDoEntry entry = new ToDoEntry(
            Objects.requireNonNullElseGet(request.getId(), UUID::randomUUID),
            request.getDueDate(),
            request.getContent(),
            false
        );

        entries.add(entry);

        return entry;
    }

    /**
     * Updates an existing {@link ToDoEntry entry} with given {@link UpdateToDoRequest data}. The
     * {@link ToDoEntry entry} with the given {@link UUID unique identifier} must exist, for the update to be
     * successful. The updated value will replace the locally existing {@link ToDoEntry entry} and will then be stored
     * locally for the runtime of the application.
     *
     * @param id      The {@link UUID unique identifier} of the {@link ToDoEntry entry}, that should be updated.
     * @param request The {@link UpdateToDoRequest data} that should be considered for the update of the
     *                {@link ToDoEntry entry}.
     *
     * @return Weather the update of the {@link ToDoEntry} was successful or not.
     */
    public boolean updateEntry(final UUID id, final UpdateToDoRequest request) {
        final var optEntry = entries.stream().filter(entry -> entry.getId().equals(id)).findFirst();
        if (optEntry.isEmpty()) return false;

        final ToDoEntry entry = optEntry.get();
        entry.setContent(request.getContent());
        entry.setDueDate(request.getDueDate());
        entry.setCompleted(request.getCompleted());

        return true;
    }

    /**
     * Deletes an existing {@link ToDoEntry entry} by its {@link UUID unique identifier}.
     *
     * @param id The {@link UUID unique identifier} of the {@link ToDoEntry entry}, that should be deleted.
     *
     * @return Weather the deletion of the {@link ToDoEntry} was successful or not.
     */
    public boolean deleteEntry(final UUID id) {
        return entries.removeIf(entry -> entry.getId().equals(id));
    }
}
