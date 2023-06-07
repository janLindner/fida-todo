package me.lindner.fidatodo.api;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import me.lindner.fidatodo.model.ToDoService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static io.restassured.RestAssured.given;

@QuarkusTest
class ToDoResourceTest {

    @Inject
    ToDoService service;

    @BeforeEach
    void setUp() {
        service.getEntries().forEach(entry -> service.deleteEntry(entry.getId()));
    }

    @Test
    @DisplayName("Should create entry")
    public void shouldCreateToDoEntry() {
        given()
            .contentType("application/json")
            .body("{\"id\": \"1f347268-c12d-47a9-8661-e8d3eb0f34db\", \"content\": \"Content\", \"dueDate\": \"05.03.2012\"}")
            .when()
            .post("/api/todos")
            .then()
            .assertThat()
            .statusCode(200)
            .and()
            .body("id", Matchers.equalTo("1f347268-c12d-47a9-8661-e8d3eb0f34db"))
            .body("content", Matchers.equalTo("Content"))
            .body("dueDate", Matchers.equalTo("05.03.2012"));
    }

    @Test
    @DisplayName("Should list entries")
    public void shouldGetEntries() {
        service.createEntry(new CreateToDoRequest(null, LocalDate.now(), "Content"));

        given()
            .accept("application/json")
            .when()
            .get("/api/todos")
            .then()
            .assertThat()
            .statusCode(200)
            .and()
            .body("size()", Matchers.is(1));
    }

    @Test
    @DisplayName("Should update entry")
    public void shouldUpdateEntry() {
        // given
        final var entry = service.createEntry(new CreateToDoRequest(null, LocalDate.now(), "Content"));

        // when
        given()
            .contentType("application/json")
            .body("{\"content\": \"Content\", \"dueDate\": \"05.03.2012\", \"completed\": \"true\"}")
            .when()
            .put("/api/todos/" + entry.getId())
            .then()
            .assertThat()
            .statusCode(204);
    }

    @Test
    @DisplayName("Should delete entry")
    public void shouldDeleteEntry() {
        service.createEntry(new CreateToDoRequest(
            UUID.fromString("d286d86d-ab95-44da-aca5-bbc805e8966c"),
            LocalDate.now(),
            "Content"
        ));

        given()
            .when()
            .delete("/api/todos/d286d86d-ab95-44da-aca5-bbc805e8966c")
            .then()
            .assertThat()
            .statusCode(204);
    }

    @Test
    @DisplayName("Should not delete not existing entry")
    public void shouldNotDeleteNotExistingEntry() {
        given()
            .when()
            .delete("/api/todos/5ed5c09c-8bfd-424d-a2fc-fd0bbb806759")
            .then()
            .assertThat()
            .statusCode(404);
    }
}
