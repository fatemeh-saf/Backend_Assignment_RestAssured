package com.fatemeh.app.backendassignmentrestassuredtest;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static io.restassured.RestAssured.given;


class TestGetJukeBoxes {

    private final String CONTEXT_PATH = "/jukebox-finder";
    private final String JSON = "application/json";
    private static String id="67ab1ec7-59b8-42f9-b96c-b261cc2a2ed9";

    @BeforeEach
    void setUp() {
        //setup base uri
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;


    }
    //http://localhost:8080/jukebox-finder/jukebox/67ab1ec7-59b8-42f9-b96c-b261cc2a2ed9?model=none&offset=0&limit=10


    @Test
    void testGetMatchedJukebox() {
        Response response =
                given().
                        accept(JSON).
                        when().
                        get(CONTEXT_PATH + "/jukebox/" + id).
                        then().
                        statusCode(200).
                        contentType(JSON).
                        extract().response();


        String bodyString=response.body().asString();
        try {
            JSONArray responseArray=new JSONArray(bodyString);
            String publicId=response.jsonPath().getString("id");
            assertNotNull(publicId);
            assertNotNull(responseArray);
        } catch (JSONException e) {
            fail(e.getMessage());
        }



    }

}