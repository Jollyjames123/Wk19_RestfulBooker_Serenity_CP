package com.heroku.restfulbooker.steps;

import com.heroku.restfulbooker.constants.EndPoints;
import com.heroku.restfulbooker.constants.Path;
import com.heroku.restfulbooker.model.BookingPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class BookingSteps {

    @Step("Creating New Booking With firstname : {0}, lastname : {1}, totalprice : {2}, depositpaid : {3}, bookingdates : {4} ")
    public ValidatableResponse createBooking(String firstname, String lastname, double totalprice, boolean depositpaid, HashMap<String, String> bookingdates) {
        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstname, lastname, totalprice, depositpaid, bookingdates);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .body(bookingPojo)
                .when()
                .post(Path.BOOKING)
                .then();
    }

    @Step("Getting The Created Booking With firstname : {0}")
    public HashMap<String, Object> getBookingWithFirstName(String firstname) {

        return         SerenityRest.given().log().all()
                        .queryParam("firstname", firstname)
                        .when()
                        .get(Path.BOOKING)
                        .then().log().all().statusCode(200)
                        .extract()
                        .path("findAll{it.bookingid}.get(0)");
    }

    @Step("Updating New Booking With id : {0}, firstname : {1}, lastname : {2}, totalprice : {3}, depositpaid : {4}, bookingdates : {5} ")
    public ValidatableResponse updateBookingWithId(int id, String firstname, String lastname, double totalprice, boolean depositpaid, HashMap<String, String> bookingdates, String key){
        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstname, lastname, totalprice, depositpaid, bookingdates);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Cookie", key)
                .pathParam("bookingID", id)
                .body(bookingPojo)
                .when()
                .put(Path.BOOKING+ EndPoints.SINGLE_BOOKING_BY_ID)
                .then();

    }

    @Step("Deleting The Updated Booking With id : {0} using authorisation key : {1}")
    public ValidatableResponse deleteBookingWithId(int id, String key){
       return SerenityRest.given().log().all()
                .header("Cookie", key)
                .pathParam("bookingID", id)
                .when()
                .delete(Path.BOOKING+EndPoints.SINGLE_BOOKING_BY_ID)
                .then();

    }
    @Step("Getting The Deleted Booking With id : {0}")
    public ValidatableResponse getBookingWithId(int id){
        return SerenityRest.given().log().all()
                .pathParam("bookingID", id)
                .when()
                .get(Path.BOOKING+EndPoints.SINGLE_BOOKING_BY_ID)
                .then();

    }
}
