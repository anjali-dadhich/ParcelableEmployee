/*Created by: Anjali Dadhich
 *Java file -- DatabaseClient (Singleton class)
 * Version -- 1.0
 * Date -- 29/1/2021
 * Last Update -- 29/1/2021
 * DatabaseClient  Creating instance of database is quite costly so we will apply a Singleton Pattern
 * to create and use already instantiated single instance for every database access.
 */
package com.example.parcelableexample.room;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient  {

    private Context context;
    private static DatabaseClient databaseClientInstance;

    //Employee database object
    private EmployeeDatabase employeeDatabase;

    //Constructor
    private DatabaseClient(Context context){
        this.context =context;

        //Creating Employee database using databaseBuilder
        //EmployeeDetail  is the name of database
        employeeDatabase = Room.databaseBuilder(context, EmployeeDatabase.class,
                "EmployeeDetail").build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (databaseClientInstance == null) {
            databaseClientInstance = new DatabaseClient(context);
        }
        return databaseClientInstance;
    }

    public EmployeeDatabase getEmployeeDatabase() {
        return employeeDatabase;
    }
}
