package com.github.sparsick.workshop;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest
public class BookRestControllerRestAssuredTests {

 
    @Autowired
    private BookRestController restController;

    @Test
    void testWithRestAssuredMockMvc() {
            RestAssuredMockMvc.standaloneSetup(restController);
            RestAssuredMockMvc.given().
                 log().all().
            when().
                 get("/book").
            then().
                 log().all()
                 .statusCode(200)
                 
                 .body("author[0]", CoreMatchers.equalTo("Erich Gamma"));
    }

    @Test
    void createBook(){
          RestAssuredMockMvc.standaloneSetup(restController);

        RestAssuredMockMvc.
        given().
          log().all().
          body(new Book()).
          contentType(ContentType.JSON).
          post("/book")
        .then()
          .statusCode(200);
    }
}
