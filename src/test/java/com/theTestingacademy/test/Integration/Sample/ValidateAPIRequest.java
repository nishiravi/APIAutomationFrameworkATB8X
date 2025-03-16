package com.theTestingacademy.test.Integration.Sample;

import com.google.gson.Gson;
import com.sun.net.httpserver.Request;
import com.theTestingacademy.Endpoints.APIConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ValidateAPIRequest {
    @Test(priority = 1)
    public static void validatecreatebooking( ITestContext iTestContext ) {

       Response response= RestAssured.given()
               .baseUri(APIConstants.BASE_URL).basePath(APIConstants.CREATE_UPDATE_BOOKING_URL)
                .contentType(ContentType.JSON)
               .body("{\n" +
                        "    \"firstname\" : \"thornew147\",\n" +
                        "    \"lastname\" : \"Ravi\",\n" +
                        "    \"totalprice\" : 111,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2018-01-01\",\n" +
                        "        \"checkout\" : \"2019-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}").when().post();
       ValidatableResponse validateresponse=response.then().log().all();
       validateresponse.statusCode(200);
       String firstname=response.jsonPath().get("booking.firstname");
       String bookingID=response.jsonPath().getString("bookingid");
        Assert.assertEquals(firstname,"thornew147");
        System.out.println("created booking"+bookingID);
        iTestContext.setAttribute("bookingid",bookingID);

    }
    @Test(priority = 2)
    public static void validateGetRequest(ITestContext iTestContext ) {
        String bookingidStr = (String) iTestContext.getAttribute("bookingid");
        Integer bookingid = Integer.parseInt(bookingidStr);
        String basepath = "/booking/" + bookingid;
        Response response = RestAssured.given().baseUri("https://restful-booker.herokuapp.com").basePath("basepath").when().get()
                .then().statusCode(200).extract().response();
        String firstname = response.jsonPath().getString("booking.firstname");
        Assert.assertEquals(firstname, "thornew147");
        System.out.println(" Firstname matched ");
    }
}
