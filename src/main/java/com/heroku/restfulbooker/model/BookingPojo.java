package com.heroku.restfulbooker.model;

import java.util.HashMap;

/**
 * {
 *     "firstname" : "Jim",
 *     "lastname" : "Brown",
 *     "totalprice" : 111,
 *     "depositpaid" : true,
 *     "bookingdates" : {
 *         "checkin" : "2018-01-01",
 *         "checkout" : "2019-01-01"
 *     },
 *     "additionalneeds" : "Breakfast"
 * }'
 */


public class BookingPojo {

    private String firstname;
    private String lastname;
    private double totalprice;
    private boolean depositpaid;
    private HashMap<String, String> bookingdates;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public HashMap<String, String> getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(HashMap<String, String> bookingdates) {
        this.bookingdates = bookingdates;
    }

    public static BookingPojo getBookingPojo(String firstname,String lastname,double totalprice,boolean depositpaid,HashMap<String, String> bookingdates){
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);
        bookingPojo.setBookingdates(bookingdates);
        return bookingPojo;
    }
}
