package com.theTestingacademy.Modules;

import com.google.gson.Gson;
import com.theTestingacademy.pojos.*;

import static org.hamcrest.MatcherAssert.assertThat;

public class PayloadManager {
    Gson gson = new Gson();

    //its job is to create a payload and  convert java object into string using GSON Library

    public String createPayloadBookingAsString() {
        // convert java object into string using GSON LIabrary-Serialization
        //Create booking API request
        Booking booking = new Booking();
        booking.setFirstname("Nishi");
        booking.setLastname("Ravi");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");
        booking.setBookingdates(bookingdates); // pass the object of BookingDate class
        booking.setAdditionalneeds("breakfast");
        System.out.println(booking);// this is an object
        //Step1:
        // convert object to JSON-SERIALIZATION
        Gson gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        System.out.println(jsonStringBooking);
        return jsonStringBooking;
    }

    // Create booking API response
    public BookingResponse bookingResponse(String responseString) {
        // convert JSON response to Object-Deserialization
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }

    // Get booking API response
    public Booking getResponseFromJSON(String getResponse) {
        Booking booking = gson.fromJson(getResponse, Booking.class);
        return booking;
    }

    //  token API request
    //  //convert object to JSON
    public String setAuthPayload() {
        // Auth Object -> json String
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        gson = new Gson();
        String jsonPayloadString = gson.toJson(auth);
        System.out.println("Payload set to the -> " + jsonPayloadString);
        return jsonPayloadString;
    }

    // Convert thr JSON to object -
    // create token response
    public String getTokenFromJSON(String tokenResponse) {
        gson = new Gson();
        AuthResponse tokenResponse1 = gson.fromJson(tokenResponse, AuthResponse.class);
        return tokenResponse1.getToken();
    }

    public String updaterequestpayload()
    {
        // update booking request body payload
        Booking booking = new Booking();
        booking.setFirstname("Sangeeth");
        booking.setLastname("Divakaran");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");
        booking.setBookingdates(bookingdates); // pass the object of BookingDate class
        booking.setAdditionalneeds("breakfast");
        System.out.println(booking);// this is an object
        //Step1:
        // convert object to JSON-SERIALIZATION
        Gson gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        System.out.println(jsonStringBooking);
        return jsonStringBooking;
    }
}
