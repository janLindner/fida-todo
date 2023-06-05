package me.lindner.fidatodo.api;

import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ToDoResourceTest {

    @Test
    @Order(1)
    @DisplayName("Should create entry")
    public void shouldCreateToDoEntry() {
        given()
            .contentType("application/json")
            .body("{\"id\": \"1f347268-c12d-47a9-8661-e8d3eb0f34db\", \"content\": \"Content\", \"dueDate\": \"2023-06-02\"}")
            .when()
            .post("/api/todos")
            .then()
            .assertThat()
            .statusCode(200)
            .and()
            .body("id", Matchers.equalTo("1f347268-c12d-47a9-8661-e8d3eb0f34db"))
            .body("content", Matchers.equalTo("Content"))
            .body("dueDate", Matchers.equalTo("2023-06-02"));
    }

    @Test
    @Order(2)
    @DisplayName("Should list entries")
    public void shouldGetEntries() {
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
    @Order(3)
    @DisplayName("Should delete entry")
    public void shouldDeleteEntry() {
        given()
            .when()
            .delete("/api/todos/1f347268-c12d-47a9-8661-e8d3eb0f34db")
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
