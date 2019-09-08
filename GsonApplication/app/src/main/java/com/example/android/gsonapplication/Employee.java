package com.example.android.gsonapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    // To store values from json, you must make variables have the same names of the key of json.
    // @SerializedName("Key_of_Json") Make jsonKey "first_name" store its value into my var "firstName"
    // Because If Json Key has not letters as the same variable Key to store its val, so will return "null"
    @SerializedName("first_name")
    // @Expose has four options,
    // 1st option is @Expose as default then.. (serialize = true, deserialize = true).
    // 2nd option is @Expose(serialize = false) then... deserialize still true).
    // 3rd option is @Expose(deserialize = false) then... serialize still true).
    // 4st option is @Expose(serialize = false, deserialize = false).

    // Note that, you must write @Expose for each variable if you want to use it.

    // serialize means convert data to json.
    // deserialize means get data from json.
    @Expose
    private String firstName;

    @SerializedName("age")
    @Expose
    private int mAge;

    @SerializedName("mail")
    @Expose
    private String mMail;

    @SerializedName("address")
    @Expose
    private Address mAddress;

    @SerializedName("family")
    @Expose
//    @Expose(serialize = false, deserialize = false)
    private ArrayList<FamilyMember> familyMembers;



    public Employee(String firstName, int mAge, String mMail, Address mAddress,ArrayList<FamilyMember> familyMembers) {
        this.firstName = firstName;
        this.mAge = mAge;
        this.mMail = mMail;
        this.mAddress = mAddress;
        this.familyMembers = familyMembers;
    }
}
