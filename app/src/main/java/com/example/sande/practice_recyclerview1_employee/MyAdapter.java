package com.example.sande.practice_recyclerview1_employee;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    List<Employee> employeeList;
    Context ctx;

    public MyAdapter(List<Employee> employeeList, Context ctx) {
        this.employeeList = employeeList;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singleitem_layout, viewGroup, false);
        return new ViewHolder(view,ctx,employeeList);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        Employee e = employeeList.get(position);
        viewHolder.tv_name.setText(e.getName());
        viewHolder.tv_age.setText(e.getAge());
        viewHolder.tv_email.setText(e.getEmail());
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_name, tv_age, tv_email;
        List<Employee> employeeList = new ArrayList<Employee>();
        Context ctx;

        public ViewHolder(View itemView,Context ctx,List<Employee> employeeList) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.employeeList = employeeList;
            this.ctx = ctx;
            tv_name = itemView.findViewById(R.id.textView_name);
            tv_age = itemView.findViewById(R.id.textView_age);
            tv_email = itemView.findViewById(R.id.textView_email);

        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Employee employee = this.employeeList.get(position);
            Intent intent = new Intent(this.ctx,EmployeeDetails.class);
            intent.putExtra("image position",position);
            intent.putExtra("name",employee.getName());
            this.ctx.startActivity(intent);
        }
    }
}
