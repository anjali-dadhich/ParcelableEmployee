/*Created by: Anjali Dadhich
 *Java file -- Employee(Model)
 * Version -- 1.0
 * Date -- 25/1/2021
 * Last Update -- 25/1/2021
 * This Class store employee detail
 */

package com.example.parcelableexample.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Employee implements Parcelable {
    private int employeeCode;
    private long mobileNo;
    private String employeeName;
    private String emailId;
    private Boolean employeeRegistered;


    //Reading Method
    //constructor used for parcel
    /* This constructor collect the values and set up the properties of the object */
    protected Employee(Parcel in) {
        employeeCode = in.readInt();
        mobileNo = in.readLong();
        employeeName = in.readString();
        emailId = in.readString();
        employeeRegistered = (Boolean) in.readValue(getClass().getClassLoader());
    }

    //Creator Method
    //Parcelable requires this method to bind everything together.
    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    //Write Method
    //this method add all your class properties to the parcel in preparation for transfer.
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(employeeCode);
        parcel.writeLong(mobileNo);
        parcel.writeString(employeeName);
        parcel.writeString(emailId);
        parcel.writeValue(employeeRegistered);
    }

    //Getter
    public int getEmployeeCode() {
        return employeeCode;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmailId() {
        return emailId;
    }

    public Boolean getEmployeeRegistered() {
        return employeeRegistered;
    }


    //constructor
    public Employee(int employeeCode, Long mobileNo, String employeeName, String emailId) {
        this.employeeCode = employeeCode;
        this.mobileNo = mobileNo;
        this.employeeName = employeeName;
        this.emailId = emailId;
    }
}
