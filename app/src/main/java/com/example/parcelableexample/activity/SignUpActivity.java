/*Created by: Anjali Dadhich
 *Java file -- SignUpActivity
 * Xml file -- activity_sign_up
 * Version -- 1.0
 * Date -- 22/1/2021
 * Last Update -- 3/2/2021
 * This One is Main Activity
 * In this Activity user enter data save in local database as list using room library
 * and pass parcelable object to CurrentEmployeeDetailDisplayActivity
 */

package com.example.parcelableexample.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcelableexample.R;
import com.example.parcelableexample.model.Employee;
import com.example.parcelableexample.room.DatabaseClient;


public class SignUpActivity extends AppCompatActivity {

    EditText editTxtEmployeeCode;
    EditText editTxtUserName;
    EditText editTxtEmailId;
    EditText editTxtMobileNo;
    Button buttonSignUp;

    private int employeeCode = 0;
    private long mobileNo =0;
    private String strEmailId;
    private String strEmployeeName;

    /* Create Object of Employee class (model) which pass to
       CurrentEmployeeDetailDisplayActivity through parcelable and use to save employee in database*/
    private Employee employee;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //initialise all Ids
        init();

        /*OnClick of buttonSignUp call setEmployeeDetail() method
        which set values of all employee detail in Employee class*/
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setEmployeeDetail();

                /*call startCurrentEmployeeDetailDisplayActivity() method*/
                if (employeeCode != 0 && mobileNo != 0 && strEmailId != null &&
                        strEmployeeName != null) {
                    startCurrentEmployeeDetailDisplayActivity();
                }
                else
                    Toast.makeText(SignUpActivity.this,
                            "Please Fill all Employee's Detail", Toast.LENGTH_SHORT).show();
            }
        });


    }

    /*  this init() method initialise  all xml views Ids */
    private void init()
    {
        editTxtEmployeeCode = findViewById(R.id.editTxtEmployeeCode);
        editTxtUserName = findViewById(R.id.editTxtUserName);
        editTxtEmailId = findViewById(R.id.editTxtEmailId);
        editTxtMobileNo = findViewById(R.id.editTxtMobileNo);
        buttonSignUp = findViewById(R.id.buttonSignUp);
    }


    /*startCurrentEmployeeDetailDisplayActivity() this method start
            CurrentEmployeeDetailDisplayActivity
            and pass objectEmployee to CurrentEmployeeDetailDisplayActivity through parcelable*/
    private void startCurrentEmployeeDetailDisplayActivity() {
        Intent iCurrentEmployeeDetailActivity = new Intent(SignUpActivity.this,
                CurrentEmployeeDetailDisplayActivity.class);
        iCurrentEmployeeDetailActivity.putExtra("Employee", employee);
        finish();
        startActivity(iCurrentEmployeeDetailActivity);
    }


    /*this Method get userInput through edit text and
    set values in variables of employee class*/
    private void setEmployeeDetail(){

        try {
            employeeCode = Integer.parseInt(editTxtEmployeeCode.getText().toString());
            mobileNo = Long.parseLong(editTxtMobileNo.getText().toString());
            strEmailId = editTxtEmailId.getText().toString();
            strEmployeeName = editTxtUserName.getText().toString();

            /*set all user entered employee detail */
            employee = new Employee(employeeCode,mobileNo,strEmployeeName,strEmailId);

            /*created an AsyncTask to perform database operation*/
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    //add Employee to database
                    //call getInstance method of DatabaseClient singleton class and pass Application Context
                    DatabaseClient.getInstance(getApplicationContext()).getEmployeeDatabase()
                            .employeeDao()
                            .insert(employee);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}