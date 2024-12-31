package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";

        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/public/v2/posts")
                .then().statusCode(200);
    }

    // Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("", hasSize(25));
    }

    // Verify the if the title of id = 184631 is equal to ”Sollicito solutio ulterius quibusdam causa appono eum suspendo thymum.”
    @Test
    public void test002() {
        response.body("find{it.id == 184631}.title", equalTo("Sollicito solutio ulterius quibusdam causa appono eum suspendo thymum."));
    }

    // Check the single user_id in the Array list (7609175)
    @Test
    public void test003() {
        response.body("user_id", hasItem(7609175));
    }

    // Check the multiple userids in the ArrayList (7609175, 7609171, 7609169)
    @Test
    public void test004() {
        response.body("user_id", hasItems(7609175, 7609171, 7609169));
    }

    // Verify the body of userid = 7609169 is equal "Canonicus defaeco suppono. Tyrannus sapiente est. Tergeo verus calamitas. Defaeco audax deduco. Ullam coadunatio degusto. Adamo delectatio convoco. Adficio consuasor suus. Cavus supplanto vacuus. Comitatus et nemo. Et eum triduana. Alii tertius conscendo. Voluptatum thesaurus nam. Voco causa charisma. Quasi vinum arbor. Testimonium et aptus. Capitulus ulterius utique. Abduco assumenda omnis. Comptus doloribus consequatur."
    @Test
    public void test005() {
        response.body("find{it.user_id == 7609169}.body", equalTo("Canonicus defaeco suppono. Tyrannus sapiente est. Tergeo verus calamitas. Defaeco audax deduco. Ullam coadunatio degusto. Adamo delectatio convoco. Adficio consuasor suus. Cavus supplanto vacuus. Comitatus et nemo. Et eum triduana. Alii tertius conscendo. Voluptatum thesaurus nam. Voco causa charisma. Quasi vinum arbor. Testimonium et aptus. Capitulus ulterius utique. Abduco assumenda omnis. Comptus doloribus consequatur."));
    }


}
