package br.com.tqi.bookstore.controller;

import br.com.tqi.bookstore.controller.dto.BookDTO;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest extends AbstractContainerBase{

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setSetUpTest() {
        RestAssured.port = randomPort;
    }

    @Test
    void whenCreateThenCheckIsCreated() {

        BookDTO bookDTO = new BookDTO();

        bookDTO.setId("1");
        bookDTO.setTitle("Harry Potter");
        bookDTO.setAuthor("J.K. Rowlling");
        bookDTO.setPublishingCompany("Rocco");
        bookDTO.setImage("imagem");
        bookDTO.setYear(2000);


        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(bookDTO)
                .post("/book")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("author", Matchers.equalTo("J.K. Rowlling"));
    }

//    @Test
//    void whenUpdadedThenCheckIsUpdaded() {
//
//
//        createDTO.setTitle("Harry potter e a pedra filosofal");
//
//        RestAssured.given()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .body(createDTO)
//                .when()
//                .put("/book/1")
//                .then()
//                .statusCode(HttpStatus.OK.value())
//                .body("title", Matchers.equalTo("Harry potter e a pedra filosofal"));
//    }

    @Test
    void whenFindAllThenCheckResult() {

        RestAssured.given()
                .when()
                .get("/book")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("author[0]", Matchers.equalTo("J.K. Rowlling"));
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }


}
