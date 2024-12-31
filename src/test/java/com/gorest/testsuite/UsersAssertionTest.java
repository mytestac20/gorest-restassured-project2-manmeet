package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UsersAssertionTest {
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

    // Verify if the total record is 20
    @Test
    public void test001() {
        response.body("", hasSize(20));
    }

    // Verify if the name of id = 7609147 is equal to ”Gajabahu Bhat”
    @Test
    public void test002() {
        response.body("find{it.id == 7609147}.name", equalTo("Gajabahu Bhat"));
    }

    // Check the single ‘Name’ in the Array list (Bhudev Varman MD)
    @Test
    public void test003() {
        response.body("name", hasItem("Bhudev Varman MD"));
    }

    // Check the multiple ‘Names’ in the ArrayList (Gajabahu Bhat,Bhudev Varman MD, Baidehi Khatri )
    @Test
    public void test004() {
        response.body("name", hasItems("Gajabahu Bhat", "Bhudev Varman MD", "Baidehi Khatri"));
    }

    // Verify the email of userid = 7609143 is equal “dhara_guha@larkin.test”
    @Test
    public void test005() {
        response.body("find{it.id == 7609143}.email", equalTo("dhara_guha@larkin.test"));
    }

    // Verify the status is “Active” of user name is “Jagdeep Pandey JD”
    @Test
    public void test006() {
        response.body("find{it.name == 'Jagdeep Pandey JD'}.status", equalTo("active"));
    }


    // Verify the Gender = female of user name is “Jagdeep Pandey JD”
    @Test
    public void test007() {
        response.body("find{it.name == 'Jagdeep Pandey JD'}.gender", equalTo("female"));
    }

}
