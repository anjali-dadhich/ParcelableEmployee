/*Created by: Anjali Dadhich
 *Java file -- EmployeeDetailDisplayRecyclerActivity
 * Xml file -- activity_employee_detail_display_recycler
 * Version -- 1.0
 * Date -- 27/1/2021
 * Last Update -- 27/1/2021
 * This activity start on click of sign up button
 */

package com.example.parcelableexample.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcelableexample.R;
import com.example.parcelableexample.adapter.EmployeeDetailRecyclerAdapter;
import com.example.parcelableexample.model.Employee;

import java.util.ArrayList;

public class EmployeeDetailDisplayRecyclerActivity extends AppCompatActivity {

    private static final String TAG ="EmployeeDetailDisplay" ;

    RecyclerView recyclerViewEmployeeDetail;
    EmployeeDetailRecyclerAdapter adapter;
    ArrayList<Employee> arrListEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail_display_recycler);

        //initialise all Ids
        init();

        //call getEmployeeDetail() and fetch all detail
        getEmployeeDetail();

    }

    /* this init() method initialise  all xml views Ids */
    private void init() {
        recyclerViewEmployeeDetail = findViewById(R.id.recyclerViewEmployeeDetail);

        //collect our intent and get objectEmployee through parcelable
        Intent intent = getIntent();
        arrListEmployee = intent.getParcelableArrayListExtra("Employee");
    }

    /*getEmployeeDetail() Method fetch all detail from Employee class
      and pass object of employee class to adapter and set those detail respectively text view */
    private void getEmployeeDetail(){
        //Set all values on text views
        adapter = new EmployeeDetailRecyclerAdapter(arrListEmployee);
        recyclerViewEmployeeDetail.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmployeeDetail.setAdapter(adapter);
    }
}