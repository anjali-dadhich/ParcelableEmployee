/*Created by: Anjali Dadhich
 *Java file -- SignUpActivity
 * Xml file -- activity_sign_up
 * Version -- 1.0
 * Date -- 22/1/2021
 * Last Update -- 25/1/2021
 * This One is Main Activity
 */

package com.example.parcelableexample.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcelableexample.R;
import com.example.parcelableexample.model.Employee;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    EditText editTxtEmployeeCode;
    EditText editTxtUserName;
    EditText editTxtEmailId;
    EditText editTxtMobileNo;
    Button buttonSignUp;

    private int employeeCode;
    private long mobileNo;
    private String strEmailId;
    private String strEmployeeName;

    /* Create Object of ArrayList of Employee class (model) which pass to
      EmployeeDetailDisplayActivity through parcelable*/
    ArrayList<Employee> arrListEmployee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //initialise all Ids
        init();

        /*OnClick of buttonSignUp call setEmployeeDetail() method
        which set values of all employee detail in Employee class
        and call startEmployeeDetailDisplayActivity() method*/
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setEmployeeDetail();

                if (employeeCode != 0 && mobileNo != 0 && strEmailId != null && strEmployeeName != null) {
                    startEmployeeDetailDisplayActivity();
                } else {
                    Toast.makeText(SignUpActivity.this, "Please Fill all Employee's Detail", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    /*startEmployeeDetailDisplayActivity() this method start EmployeeDetailDisplayActivity
      and pass objectEmployee to EmployeeDetailDisplayActivity*/
    private void startEmployeeDetailDisplayActivity() {
        Intent iEmployeeDetailDisplayActivity = new Intent(SignUpActivity.this,
                EmployeeDetailDisplayRecyclerActivity.class);
        iEmployeeDetailDisplayActivity.putParcelableArrayListExtra("Employee" , arrListEmployee);
        startActivity(iEmployeeDetailDisplayActivity);
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

    /*this Method get userInput through edit text and
    set values in variables of employee class*/
    private void setEmployeeDetail(){

        try {
            employeeCode = Integer.parseInt(editTxtEmployeeCode.getText().toString());
            mobileNo = Long.parseLong(editTxtMobileNo.getText().toString());
            strEmailId = editTxtEmailId.getText().toString();
            strEmployeeName = editTxtUserName.getText().toString();

            /*initialise object at time of adding item in arrayList to prevent add same item at time
            of reload activity*/
            arrListEmployee = new ArrayList<>();

            /*add item in arrListEmployee using employee object*/
            arrListEmployee.add(new Employee(30031,9397345330L,"Anjali", "anjali@rathi.com"));
            arrListEmployee.add(new Employee(30032,9397343489L,"Anjali Dadhich", "anjali21@rathi.com"));
            arrListEmployee.add(new Employee(30033,9399865423L,"Anjali Vyas", "anjali94@rathi.com"));
            arrListEmployee.add(new Employee(30034,7845621098L,"Ankita Vyas", "ankita@rathi.com"));
            arrListEmployee.add(new Employee(30035,6548399201L,"Anand", "anand@rathi.com"));

            /*add user entered detail in arrListEmployee using employee object*/
            arrListEmployee.add(new Employee(employeeCode,mobileNo,strEmployeeName,strEmailId));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}