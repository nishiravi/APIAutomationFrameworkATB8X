package com.theTestingacademy.BaseTest;


import com.theTestingacademy.Asserts.AssertActions;
import com.theTestingacademy.Endpoints.APIConstants;
import com.theTestingacademy.Modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BaseTest {
    // contains all the code to run before and after the Test (test cases)
    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonpath;
    public Response response;
    public ValidatableResponse validatableResponse;

    @BeforeTest
            @Test(groups = "qa", priority = 1)
    public void setup() {
        //BaseURL,Content Type
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(APIConstants.BASE_URL);
        requestSpecification.contentType(ContentType.JSON).log().all();
    }

    public String getToken() {
        // get  the token
        requestSpecification = RestAssured
                .given()
                .baseUri(APIConstants.BASE_URL)
                .basePath(APIConstants.AUTH_URL);
        // Setting the payload
        String JSONTokenRequestpayload = payloadManager.setAuthPayload();
        // Get the Token
        response = requestSpecification.contentType(ContentType.JSON).body(JSONTokenRequestpayload).when().post();
        //  Get Token resposne Extraction
        String token = payloadManager.getTokenFromJSON(response.asString());
        return token;
    }
}
