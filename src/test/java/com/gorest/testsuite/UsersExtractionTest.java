package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UsersExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";

        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/public/v2/users")
                .then().statusCode(200);
    }

    // Extract the All Ids
    @Test
    public void test001() {
        List<Integer> ids = response.extract().path("id");
        System.out.println("All Ids : " + ids);
    }

    // Extract All names
    @Test
    public void test002() {
        List<String> names = response.extract().path("name");
        System.out.println("All names : " + names);
    }

    // Extract the name of 5th object
    @Test
    public void test003() {
        String name = response.extract().path("[4].name");
        System.out.println("The name of 5th object : " + name);
    }

    // Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List<String> names = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("The names of all object whose status = inactive : " + names);
    }

    // Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<String> gender = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("The gender of all the object whose status = active : " + gender);
    }

    // Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<String> name = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("The names of the object whose gender = female : " + name);
    }

    // Get all the emails of the object where status = inactive
    @Test
    public void test007() {
        List<String> email = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("The emails of the object where status = inactive : " + email);
    }

    // Get the ids of the object where gender = male
    @Test
    public void test008() {
        List<Integer> ids = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("The ids of the object where gender = male : " + ids);
    }

    // Get all the status
    @Test
    public void test009() {
        List<String> status = response.extract().path("status");
        System.out.println("All the status : " + status);
    }

    // Get email of the object where name Gajabahu Bhat
    @Test
    public void test010() {
        String email = response.extract().path("find{it.name == 'Gajabahu Bhat'}.email");
        System.out.println("All the status : " + email);
    }

    // Get gender of id = 7609143
    @Test
    public void test011() {
        String id = response.extract().path("find{it.id == 7609143}.gender");
        System.out.println("gender of id = 7609143 : " + id);
    }


}
