package com.heroku.restfulbooker.testinfo;

import com.heroku.restfulbooker.constants.EndPoints;
import com.heroku.restfulbooker.constants.Path;
import com.heroku.restfulbooker.model.BookingPojo;
import com.heroku.restfulbooker.steps.BookingSteps;
import com.heroku.restfulbooker.testbase.TestBase;
import com.heroku.restfulbooker.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

@RunWith(SerenityRunner.class)
public class BookingCRUDWithSteps extends TestBase {

    /**
     * private String firstname;
     * private String lastname;
     * private double totalprice;
     * private boolean depositpaid;
     * private HashMap<String, Object> bookingdates;
     * "username" : "admin",
     * "password" : "password123"
     */

    static String firstname = "Harry" + TestUtils.getRandomValue();
    static String lastname = "Potter" + TestUtils.getRandomValue();
    static double totalprice = 999;
    static boolean depositpaid;
    static int id;
    static String key;


    @Steps
    BookingSteps bookingSteps;

    @Title("This will create a new Booking")
    @Test
    public void test001() {
        HashMap<String, String> dates = new HashMap<>();
        dates.put("checkin", "2013-02-23");
        dates.put("checkout", "2014-10-23");

        ValidatableResponse response =
                bookingSteps.createBooking(firstname, lastname, totalprice, depositpaid, dates);

        response.log().all().statusCode(200);

    }

    @Title("This will verify if the New Booking is added to the records")
    @Test
    public void test002() {

        HashMap<String, Object> value =
                bookingSteps.getBookingWithFirstName(firstname);

        id = (int) value.get("bookingid");
        System.out.println("Id of the added Booking is :" + id);

    }

    @Title("This will update the Booking information and verify the updated information")
    @Test
    public void test003() {
        String authBody = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        String authToken = SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .body(authBody)
                .when()
                .post(Path.AUTHORISATION)
                .then().log().all()
                .statusCode(200)
                .extract().path("token");

        System.out.println("Token is :" + authToken);
        key = "token=" + authToken;

        firstname = firstname + "oak";
        HashMap<String, String> dates = new HashMap<>();
        dates.put("checkin", "2013-02-23");
        dates.put("checkout", "2014-10-23");

        ValidatableResponse response =
                bookingSteps.updateBookingWithId(id, firstname, lastname, totalprice, depositpaid, dates, key);
        response.log().all().statusCode(200);

    }

    @Title("This will delete the Booking and verify if the Booking is deleted")
    @Test
    public void test004() {

        bookingSteps.deleteBookingWithId(id, key).log().all().statusCode(201);
        bookingSteps.getBookingWithId(id).log().all().statusCode(404);

        //.header("cookie", "token=2b60f78007a66ac")
    }


}
