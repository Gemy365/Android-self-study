package com.example.android.gsonapplication;

import com.google.gson.annotations.Expose;

public class Address {
    @Expose
    private String Country;
    @Expose
    private String City;

    public Address(String country, String city) {
        Country = country;
        City = city;
    }
}
