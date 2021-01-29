/*Created by: Anjali Dadhich
 *Java file -- EmployeeDetailRecyclerAdapter
 * Version -- 1.0
 * Date -- 27/1/2021
 * Last Update -- 27/1/2021
 * It is a Recycler Adapter for EmployeeDetailDisplayRecyclerActivity
 * Layout Inflate recycler_employee_list_raw
 */

package com.example.parcelableexample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcelableexample.R;
import com.example.parcelableexample.model.Employee;

import java.util.ArrayList;

public class EmployeeDetailRecyclerAdapter extends RecyclerView.Adapter<EmployeeDetailRecyclerAdapter.ViewHolder> {

    //create ArrayList of employee type
    private ArrayList<Employee> arrListEmployee;

    //Constructor of adapter class
    public EmployeeDetailRecyclerAdapter(ArrayList<Employee> arrListEmployee) {
        this.arrListEmployee = arrListEmployee;
    }

    @NonNull
    @Override
    public EmployeeDetailRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.recycler_employee_list_raw, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeDetailRecyclerAdapter.ViewHolder holder, int position) {

        try {
            holder.txtEmailId.setText(String.valueOf(arrListEmployee.get(position).getEmailId()));
            holder.txtEmployeeCode.setText(String.valueOf(arrListEmployee.get(position).getEmployeeCode()));
            holder.txtMobileNo.setText(String.valueOf(arrListEmployee.get(position).getMobileNo()));
            holder.txtName.setText(arrListEmployee.get(position).getEmployeeName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return (arrListEmployee == null) ? 0 : arrListEmployee.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //declare view of recycler_employee_list_row
        TextView txtEmployeeCode;
        TextView txtName;
        TextView txtMobileNo;
        TextView txtEmailId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //find Ids
            txtEmployeeCode = itemView.findViewById(R.id.txtEmployeeCode);
            txtName = itemView.findViewById(R.id.txtName);
            txtMobileNo = itemView.findViewById(R.id.txtMobileNo);
            txtEmailId = itemView.findViewById(R.id.txtEmailId);
        }
    }
}
