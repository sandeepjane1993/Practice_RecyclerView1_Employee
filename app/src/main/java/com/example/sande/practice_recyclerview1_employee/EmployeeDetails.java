package com.example.sande.practice_recyclerview1_employee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class EmployeeDetails extends AppCompatActivity {

    int[] Images = {R.drawable.apple_logo, R.drawable.android_logo, R.drawable.csharp_logo, R.drawable.java_logo,R.drawable.nike_logo};
    ImageView iv;
    TextView tv_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_details_layout);

        iv = findViewById(R.id.imageView);
        tv_name = findViewById(R.id.tv_details_name);

        int position = getIntent().getExtras().getInt("image position");
        iv.setImageResource(Images[position]);
        //tv_name.setText("Name :" + getIntent().getStringExtra("name"));

    }
}
