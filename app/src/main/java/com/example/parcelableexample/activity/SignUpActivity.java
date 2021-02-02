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

    private int employeeCode;
    private long mobileNo;
    private String strEmailId;
    private String strEmployeeName;

    /* Create Object of Employee class (model) which pass to
       CurrentEmployeeDetailDisplayActivity through parcelable and use to save employee in database*/
    Employee employee;

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

    /*this Method get userInput through edit text and
    set values in variables of employee class*/
    private void setEmployeeDetail(){

        /*created an AsyncTask to perform database operation*/
         class  SaveEmployeeDetailInDatabase extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {

                try {
                    employeeCode = Integer.parseInt(editTxtEmployeeCode.getText().toString());
                    mobileNo = Long.parseLong(editTxtMobileNo.getText().toString());
                    strEmailId = editTxtEmailId.getText().toString();
                    strEmployeeName = editTxtUserName.getText().toString();

                    /*set all user entered employee detail */
                    employee = new Employee(employeeCode,mobileNo,strEmployeeName,strEmailId);

                    //add Employee to database
                    //call getInstance method of DatabaseClient singleton class and pass Application Context
                    DatabaseClient.getInstance(getApplicationContext()).getEmployeeDatabase()
                            .employeeDao()
                            .insert(employee);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                /*call startCurrentEmployeeDetailDisplayActivity() method*/
                if (employeeCode != 0 && mobileNo != 0 && strEmailId != null &&
                        strEmployeeName != null) {
                    startCurrentEmployeeDetailDisplayActivity();
                }
                else
                    Toast.makeText(SignUpActivity.this,
                            "Please Fill all Employee's Detail", Toast.LENGTH_SHORT).show();
            }

            /*startCurrentEmployeeDetailDisplayActivity() this method start
              CurrentEmployeeDetailDisplayActivity
              and pass objectEmployee to CurrentEmployeeDetailDisplayActivity through parcelable*/
            private void startCurrentEmployeeDetailDisplayActivity() {
                Intent iCurrentEmployeeDetailActivity = new Intent(SignUpActivity.this,
                        CurrentEmployeeDetailDisplayActivity.class);
                iCurrentEmployeeDetailActivity.putExtra("Employee" , employee);
                finish();
                startActivity(iCurrentEmployeeDetailActivity);
            }
        }

        /*Created Object and execute SaveEmployeeDetailInDatabase AsycTask class*/
        SaveEmployeeDetailInDatabase saveEmployeeDetailInDatabase = new SaveEmployeeDetailInDatabase();
        saveEmployeeDetailInDatabase.execute();

    }

}