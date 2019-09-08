package com.example.android.gsonapplication;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // GsonBuilder().excludeFieldsWithoutExposeAnnotation().create() Allow you to use [@Expose]
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        Address address = new Address("Egypt", "October");

        ArrayList<FamilyMember> family = new ArrayList<>();
        family.add(new FamilyMember("Wife", 24));
        family.add(new FamilyMember("Daughter", 20));

        // Serialization Process [Make data As json].
        Employee employee = new Employee("Gemy", 24, "Mohamedgamal@yahoo.com", address, family);
        String json = gson.toJson(employee);

        // DeSerialization Process [Get data from json].
//         String json = "{\"family\":[{\"age\":24,\"role\":\"Wife\"},{\"age\":20,\"role\":\"Daughter\"}],\"first_name\":\"Gemy\",\"address\":{\"City\":\"October\",\"Country\":\"Egypt\"},\"age\":24,\"mail\":\"Mohamedgamal@yahoo.com\"}";
//         Employee employee = gson.fromJson(json, Employee.class);
    }
}
