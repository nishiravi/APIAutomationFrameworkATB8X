package com.theTestingacademy.test.Integration.Sample;

import com.fasterxml.jackson.databind.util.JSONPObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class DynamicPaylodTest {

    @Test
    public void validateUsers()
    {
        JSONObject  request=new JSONObject ();
        request.put("name","Users"+System.currentTimeMillis());
        request.put("email","Users"+Math.random()+"@examplemail.com");

        Response response=given().baseUri("https://reqres.in/api")  // API Base URL
            .basePath("/users").contentType(ContentType.JSON).body(request.toString()).when().post();
        ValidatableResponse validatableResponse=response.then().log().all();
        validatableResponse.statusCode(201);
        System.out.println(response.prettyPrint());

        String name=response.jsonPath().getString("name");
        System.out.println(name);
    }
}
