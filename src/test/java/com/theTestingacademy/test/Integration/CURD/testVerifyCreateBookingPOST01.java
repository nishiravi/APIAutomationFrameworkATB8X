package com.theTestingacademy.test.Integration.CURD;

import com.theTestingacademy.BaseTest.BaseTest;
import com.theTestingacademy.Endpoints.APIConstants;
import com.theTestingacademy.pojos.BookingResponse;
import io.qameta.allure.Description;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class testVerifyCreateBookingPOST01 extends BaseTest {

    @Description(" Verify if the create booking post request is working fine ")
    @Test(groups = "qa")
    public void testVerifyCreateBookingPOST01() {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = requestSpecification.when().body(payloadManager.createPayloadBookingAsString()).post();
        Integer bookingid = response.then().extract().path("bookingid");
        System.out.println("bookingid is " + bookingid);
        validatableResponse = response.then().log().all();
        //Default restAssured Validation
        validatableResponse.statusCode(200);
        validatableResponse.body("booking.firstname", Matchers.equalTo("Nishi"));

        //AssertJ validation
        // Converting JSON string to object and assigning it to booking reponse class
        BookingResponse bookingResponse = payloadManager.bookingResponse(response.asString());
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBookingid()).isNotZero().isPositive();
        assertThat(bookingResponse.getBooking().getFirstname()).isAlphabetic().isEqualTo("Nishi");
        // TestNG assertion
        assertActions.verifyStatusCode(response, 200);
    }
}
