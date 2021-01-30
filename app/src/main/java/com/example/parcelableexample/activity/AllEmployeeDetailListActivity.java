/*Created by: Anjali Dadhich
 *Java file -- AllEmployeeDetailListActivity
 * Xml file -- activity_all_employee_detail_list
 * Version -- 1.0
 * Date -- 27/1/2021
 * Last Update -- 27/1/2021
 * This activity start on click of all employee button in CurrentEmployeeDetailDisplayActivity
 */

package com.example.parcelableexample.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.parcelableexample.R;
import com.example.parcelableexample.adapter.EmployeeDetailRecyclerAdapter;
import com.example.parcelableexample.model.Employee;
import com.example.parcelableexample.room.DatabaseClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AllEmployeeDetailListActivity extends AppCompatActivity {

    private static final String TAG ="EmployeeDetailDisplay" ;

    RecyclerView recyclerViewEmployeeDetail;
    FloatingActionButton floatingButtonAddEmployee;
    EmployeeDetailRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_employee_detail_list);

        //initialise all Ids
        init();

        //call getEmployeeDetail() and fetch all detail from database
        getEmployeeDetail();

        floatingButtonAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignUpButton();
            }
        });

    }

    /* this init() method initialise  all xml views Ids */
    private void init() {
        recyclerViewEmployeeDetail = findViewById(R.id.recyclerViewEmployeeDetail);

    }

    private void startSignUpButton(){
        Intent iSignUpActivity = new Intent(AllEmployeeDetailListActivity.this,SignUpActivity.class);
        startActivity(iSignUpActivity);
    }

    /*getEmployeeDetail() Method fetch all detail from database
      and pass object of employeeArrayList to adapter and set those detail respectively text view */
    private void getEmployeeDetail(){

        /*created an AsyncTask to perform database operation*/
        class GetEmployeeDetailFromDatabase extends AsyncTask<Void,Void, List<Employee>>{

            @Override
            protected List<Employee> doInBackground(Void... voids) {

                return   DatabaseClient
                        .getInstance(getApplicationContext())
                        .getEmployeeDatabase()
                        .employeeDao()
                        .getAll();
            }

            @Override
            protected void onPostExecute(List<Employee> employeeArrayList) {
                super.onPostExecute(employeeArrayList);

                //Set all values on text views
                adapter = new EmployeeDetailRecyclerAdapter(employeeArrayList);
                recyclerViewEmployeeDetail.setLayoutManager(
                        new LinearLayoutManager(AllEmployeeDetailListActivity.this));
                recyclerViewEmployeeDetail.setAdapter(adapter);
            }
        }

        /*Created Object and execute GetEmployeeDetailFromDatabase AsycTask class*/
        GetEmployeeDetailFromDatabase getEmployeeDetailFromDatabase = new GetEmployeeDetailFromDatabase();
        getEmployeeDetailFromDatabase.execute();
    }
}