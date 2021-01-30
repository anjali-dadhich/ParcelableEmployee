/*Created by: Anjali Dadhich
 *Java file -- EmployeeDatabase (abstract class)
 * Version -- 1.0
 * Date -- 29/1/2021
 * Last Update -- 29/1/2021
 * An abstract class annotated 	with @Database annotation is used to create a database
 * with given name 	along with database version.
 */
package com.example.parcelableexample.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.parcelableexample.model.Employee;

@Database(entities = {Employee.class}, version = 1)
public abstract class EmployeeDatabase extends RoomDatabase {

    public abstract EmployeeDao employeeDao();
}
