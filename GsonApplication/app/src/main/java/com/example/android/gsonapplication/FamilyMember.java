package com.example.android.gsonapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FamilyMember {

    @SerializedName("role")
    @Expose
    private String mRole;

    @SerializedName("age")
    @Expose
    private int mAge;


    public FamilyMember(String mRole, int mAge) {
        this.mRole = mRole;
        this.mAge = mAge;
    }
}
