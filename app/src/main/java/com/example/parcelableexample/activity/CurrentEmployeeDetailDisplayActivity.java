/*Created by: Anjali Dadhich
 *Java file -- CurrentEmployeeDetailDisplayActivity
 * Xml file -- activity_current_employee_detail_display
 * Version -- 1.0
 * Date -- 30/1/2021
 * Last Update -- 2/1/2021
 * This activity start on click of signUp button in SignUpActivity
 * In This activity data access using Parcelable Object
 */

package com.example.parcelableexample.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.parcelableexample.R;
import com.example.parcelableexample.model.Employee;

public class CurrentEmployeeDetailDisplayActivity extends AppCompatActivity {

    TextView txtEmployeeCode;
    TextView txtName;
    TextView txtMobileNo;
    TextView txtEmailId;
    Button buttonEmployeeList;

    //Object of employee class which initialise using parcelable
    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_employee_detail_display);

        //initialise all Ids
        init();

        //get user entered employee detail and set those parameters on respectively text views
        getCurrentEmployeeDetail();

        buttonEmployeeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAllEmployeeDetailList();
            }
        });
    }

    /* this init() method initialise  all xml views Ids */
    private void init() {
        txtEmployeeCode = findViewById(R.id.txtEmployeeCode);
        txtName = findViewById(R.id.txtName);
        txtMobileNo = findViewById(R.id.txtMobileNo);
        txtEmailId = findViewById(R.id.txtEmailId);
        buttonEmployeeList = findViewById(R.id.buttonEmployeeList);

        //collect our intent and get objectEmployee through parcelable
        Intent intent = getIntent();
        employee = intent.getParcelableExtra("Employee");
    }

    private void getCurrentEmployeeDetail(){
        txtName.setText(employee.getEmployeeName());
        txtEmailId.setText(employee.getEmailId());
        txtEmployeeCode.setText(String.valueOf(employee.getEmployeeCode()));
        txtMobileNo.setText(String.valueOf(employee.getMobileNo()));
    }

    private void startAllEmployeeDetailList(){
        Intent iAllEmployeeDetailListActivity = new Intent(
                CurrentEmployeeDetailDisplayActivity.this,
                AllEmployeeDetailListActivity.class);
        startActivity(iAllEmployeeDetailListActivity);
    }
}