package com.theTestingacademy.test.Integration.CURD;

import com.theTestingacademy.BaseTest.BaseTest;
import com.theTestingacademy.Endpoints.APIConstants;
import com.theTestingacademy.pojos.Booking;
import com.theTestingacademy.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestIntegrationFlow extends BaseTest {
    // Create A Booking, Create a Token
    // Get booking
    // Update the Booking
    // Delete the Booking


    @Test(groups = "qa", priority = 1)
    @Owner("Nishi")
    @Description("TC#INT1 - Step 1. Verify CreateBooking API")
    // method to create and get the booking ID
    //POST REQ
    public void testCreateBooking(ITestContext iTestContext) {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification).when().body(payloadManager.createPayloadBookingAsString()).post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        BookingResponse bookingResponse = payloadManager.bookingResponse(response.asString());
        Integer createdbookingid = bookingResponse.getBookingid();
        System.out.println(" created booking id is " + createdbookingid);
        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());

    }

    @Test(groups = "qa", priority = 2)
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
    //GET Request
    public void testVerifyBookingId(ITestContext iTestContext) {
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String basepath = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        requestSpecification.basePath(basepath);
        response = RestAssured.given(requestSpecification).when().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        Booking booking = payloadManager.getResponseFromJSON(response.asString());
        assertThat(assertThat(booking.getFirstname()).isNotNull().isAlphanumeric());
    }

    @Test(groups = "qa", priority = 3)
    @Owner("Nishi")
    @Description("TC#INT1 - Step 3. Verify Updated Booking by ID")
    public void testUpdateBookingByID(ITestContext iTestContext) {
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = getToken();
        iTestContext.setAttribute("token", token);
        String basepath=APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        requestSpecification.basePath(basepath).cookie("token",token);
        response = RestAssured.given(requestSpecification).when().body(payloadManager.updaterequestpayload()).put();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        Booking booking = payloadManager.getResponseFromJSON(response.asString());
        assertThat(booking.getFirstname()).isEqualTo("Sangeeth");

    }


    @Test(groups = "qa", priority = 4)
    @Owner("Nishi")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext) {
        String token = (String) iTestContext.getAttribute("token");
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        System.out.println(" booking id to be deleted is  is " + bookingid);
        String basepath = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        requestSpecification.basePath(basepath).cookie("token",token);
        response = RestAssured.given(requestSpecification).when().delete();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);


    }

}
