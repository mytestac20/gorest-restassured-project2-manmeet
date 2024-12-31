package com.gorest.crudtest;

import com.gorest.constant.EndPoints;
import com.gorest.model.PostsPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostTest extends TestBase {

    static int id;
    static int created_user_id = 7605727;
    static String created_title = "Delibero amaritudo adfectus natus pauper charisma quia delinquo accusatoruu.";
    static String created_body = "Vulticulus volup carpo. Et truculenter nostrum. Tabella comes magni. Surgo laboriosam comedo. Iste bos conduco. Solvo vomica ex. Fuga decretum ante.";

    @Test(priority = 1)
    public void getListOfPosts() {
        Map<String, Integer> queryParams = new HashMap<>();
        queryParams.put("page", 1);
        queryParams.put("per_page", 25);

        ValidatableResponse response = given().log().all()
                .header("Content-Type", "application/json")
                .queryParams(queryParams)
                .when()
                .get(EndPoints.GET_ALL_POSTS)
                .then().log().all().statusCode(200);
        id = response.extract().path("[0].id");
    }

    @Test(priority = 2)
    public void getPostsById() {
        ValidatableResponse response = given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", id)
                .when()
                .get(EndPoints.GET_POSTS_BY_ID)
                .then().log().all().statusCode(200);
    }

    @Test(priority = 3)
    public void createPostsRecord() {

        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setUser_id(created_user_id);
        postsPojo.setTitle(created_title);
        postsPojo.setBody(created_body);


        ValidatableResponse response = given().log().all()
                .header("Accept", "application/json")
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer e832a705655493c7e143306a74ee223819a9c96b6376f7603bcebd4887434d6e")
                .when()
                .body(postsPojo)
                .post(EndPoints.CREATE_POSTS_RECORD)
                .then().log().all().statusCode(201);
        id = response.extract().path("id");

    }

    @Test(priority = 4)
    public void updatePostsRecord() {

        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setUser_id(created_user_id);
        postsPojo.setTitle(created_title + "updated");
        postsPojo.setBody(created_body + "updated");

        ValidatableResponse response = given().log().all()
                .header("Accept", "application/json")
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer e832a705655493c7e143306a74ee223819a9c96b6376f7603bcebd4887434d6e")
                .pathParam("id", id)
                .when()
                .body(postsPojo)
                .put(EndPoints.UPDATE_POSTS)
                .then().log().all().statusCode(200);

    }

    @Test(priority = 5)
    public void partiallyUpdatePostsRecord() {

        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setUser_id(created_user_id);
        postsPojo.setTitle(created_title + "updated");
        postsPojo.setBody(created_body);

        ValidatableResponse response = given().log().all()
                .header("Accept", "application/json")
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer e832a705655493c7e143306a74ee223819a9c96b6376f7603bcebd4887434d6e")
                .pathParam("id", id)
                .when()
                .body(postsPojo)
                .patch(EndPoints.PARTIALLY_UPDATE_POSTS)
                .then().log().all().statusCode(200);
    }

    @Test(priority = 6)
    public void deletePostRecord() {
        given().log().all()
                .header("Accept", "application/json")
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer e832a705655493c7e143306a74ee223819a9c96b6376f7603bcebd4887434d6e")
                .pathParam("id", id)
                .when()
                .delete(EndPoints.DELETE_POST)
                .then().log().all()
                .statusCode(204);
    }
}