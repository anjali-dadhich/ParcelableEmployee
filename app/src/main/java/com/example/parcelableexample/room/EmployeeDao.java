/*Created by: Anjali Dadhich
 *Java file -- EmployeeDao Interface
 * Version -- 1.0
 * Date -- 29/1/2021
 * Last Update -- 29/1/2021
 * This Class an interface  class
 * annotated with @Doa annotation, containing all the methods to define the
 * operations to be performed on data.
 */

package com.example.parcelableexample.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.parcelableexample.model.Employee;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface EmployeeDao {
    @Query("SELECT * FROM employee")
    List<Employee> getAll();

    @Insert
    void insert(Employee employee);

    @Delete
    void delete(Employee employee);

    @Update
    void update(Employee employee);
}
