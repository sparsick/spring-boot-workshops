package com.github.sparsick.workshop;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest
public class BookRestControllerRestAssuredTests {

 
    @Autowired
    private BookRestController restController;

    @Test
    public void testWithRestAssuredMockMvc() {
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
}
