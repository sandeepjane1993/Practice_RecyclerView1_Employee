package com.example.sande.practice_recyclerview1_employee;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Employee> myList;
    MyAdapter adapter;
    RecyclerView recyclerView;
    String url = "https://api.androidhive.info/contacts/";
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myList = new ArrayList<>();
        adapter = new MyAdapter(myList,this);


        recyclerView = findViewById(R.id.recyclerView);

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        /*recyclerView.setAdapter(adapter);*/

        /*createData();*/

        dialog = new ProgressDialog(this);
        dialog.setTitle("Title");
        dialog.setMessage("downloading the data");
        dialog.show();
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("contacts");
                    for(int i=0;i< jsonArray.length();i++)
                    {
                        JSONObject mydata = jsonArray.getJSONObject(i);
                        String myName = mydata.getString("name");
                        String myAge = mydata.getString("id");
                        String myEmail = mydata.getString("email");
                        Employee e = new Employee(myName,myAge,myEmail);
                        myList.add(e);

                    }
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void createData()
    {
        Employee e1 = new Employee("sandeep","25","gmail");
        myList.add(e1);
        Employee e2 = new Employee("cijan","26","outlook");
        myList.add(e2);
        Employee e3 = new Employee("yixin","23","github");
        myList.add(e3);
        Employee e4 = new Employee("sandra","22","unknown");
        myList.add(e4);
        Employee e5 = new Employee("berry","34","yahoo");
        myList.add(e5);
    }
}
