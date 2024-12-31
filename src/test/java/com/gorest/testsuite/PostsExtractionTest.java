package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
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

    // Extract the title
    @Test
    public void test001() {
        List<String> title = response.extract().path("title");
        System.out.println("The title : " + title);
    }

    // Extract the total number of record
    @Test
    public void test002() {
        List<String> title = response.extract().path("");
        System.out.println("The total number of record : " + title.size());
    }

    // Extract the body of 15th record
    @Test
    public void test003() {
        String data = response.extract().path("[14].body");
        System.out.println("The body of 15th record : " + data);
    }

    // Extract the user_id of all the records
    @Test
    public void test004() {
        List<Integer> data = response.extract().path("user_id");
        System.out.println("The user_id of all the records : " + data);
    }

    // Extract the title of all the records
    @Test
    public void test005() {
        List<Integer> data = response.extract().path("title");
        System.out.println("The title of all the records : " + data);
    }

    // Extract the title of all records whose user_id = 7604995
    @Test
    public void test006() {
        List<String> data = response.extract().path("findAll{it.user_id == 7604995}.title");
        System.out.println("The title of all records whose user_id = 7604995 : " + data);
    }

    // Extract the body of all records whose user_id = 7604986
    @Test
    public void test007() {
        List<String> data = response.extract().path("findAll{it.user_id == 7015118}.body");
        System.out.println("The body of all records whose user_id = 7604986 : " + data);
    }
}
