package me.lindner.fidatodo.model;

import me.lindner.fidatodo.api.CreateToDoRequest;
import me.lindner.fidatodo.api.UpdateToDoRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

class ToDoServiceTest {

    private ToDoService service;

    @BeforeEach
    void setUp() {
        service = new ToDoService();
    }

    @Test
    @DisplayName("Should create entry without given id")
    void shouldCreateToDoEntryWithoutGivenId() {
        // given
        final var request = new CreateToDoRequest(null, LocalDate.now(), "Test Content");

        // when
        final var createdEntry = service.createEntry(request);

        // then
        final var queriedEntry = service.getEntries().stream().filter(entry -> entry.getId() == createdEntry.getId()).findFirst().orElseThrow();
        Assertions.assertEquals(request.getDueDate(), createdEntry.getDueDate());
        Assertions.assertEquals(request.getDueDate(), queriedEntry.getDueDate());

        Assertions.assertEquals(request.getContent(), createdEntry.getContent());
        Assertions.assertEquals(request.getContent(), queriedEntry.getContent());
        Assertions.assertFalse(createdEntry.isCompleted());
        Assertions.assertEquals(1, service.getEntries().size());
    }

    @Test
    @DisplayName("Should create entry with given id")
    void shouldCreateToDoEntryWithGivenId() {
        // given
        final var request = new CreateToDoRequest(UUID.randomUUID(), LocalDate.now(), "Test Content");
        final var expectedEntry = new ToDoEntry(request.getId(), request.getDueDate(), request.getContent(), false);

        // when
        final var createdEntry = service.createEntry(request);

        // then
        final var queriedEntry = service.getEntries().stream().filter(entry -> entry.getId() == createdEntry.getId()).findFirst().orElseThrow();
        Assertions.assertEquals(expectedEntry, createdEntry);
        Assertions.assertEquals(expectedEntry, queriedEntry);
        Assertions.assertEquals(1, service.getEntries().size());
    }

    @Test
    @DisplayName("Should delete entry")
    void shouldDeleteToDoEntry() {
        // given
        final var request = new CreateToDoRequest(UUID.randomUUID(), LocalDate.now(), "Test Content");
        final ToDoEntry entry = service.createEntry(request);

        // when
        final boolean deleted = service.deleteEntry(entry.getId());

        // then
        Assertions.assertTrue(deleted);
        Assertions.assertTrue(service.getEntries().isEmpty());
    }

    @Test
    @DisplayName("Should update entry")
    void shouldUpdateEntry() {
        // given
        final var createRequest = new CreateToDoRequest(UUID.randomUUID(), LocalDate.now(), "Test Content");
        final ToDoEntry createdEntry = service.createEntry(createRequest);
        final var updateRequest = new UpdateToDoRequest(LocalDate.now(), "Other Content", true);
        final var expectedEntry = new ToDoEntry(createdEntry.getId(), updateRequest.getDueDate(), updateRequest.getContent(), updateRequest.getCompleted());

        // when
        final boolean deleted = service.updateEntry(createdEntry.getId(), updateRequest);
        final var updatedEntry = service.getEntries().stream().filter(entry -> entry.getId() == createdEntry.getId()).findFirst().orElseThrow();

        // then
        Assertions.assertTrue(deleted);
        Assertions.assertEquals(expectedEntry, updatedEntry);
        Assertions.assertEquals(1, service.getEntries().size());
    }
}
