package com.example.canteen_system;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Objects;

public class admin_student_adapter extends ArrayAdapter<students_detail> {
    private Context mcontext;
    private int mresource;
    public admin_student_adapter(@NonNull Context context, int resource, @NonNull ArrayList<students_detail> objects) {
        super(context, resource, objects);
        mcontext = context;
        mresource = resource;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String fname = Objects.requireNonNull(getItem(position)).getfName();
        String semester = Objects.requireNonNull(getItem(position)).getSemester();
        String address = Objects.requireNonNull(getItem(position)).getAddress();
        String mobile = Objects.requireNonNull(getItem(position)).getMobile();
        String dob = Objects.requireNonNull(getItem(position)).getDob();

        LayoutInflater inflater = LayoutInflater.from(mcontext);
        convertView = inflater.inflate(mresource,parent,false);
        TextView Fname,Semester,Number,DOB,Address;
        Fname = convertView.findViewById(R.id.name_student_container);
        Semester = convertView.findViewById(R.id.semester_student_container);
        Number = convertView.findViewById(R.id.number_student_container);
        DOB = convertView.findViewById(R.id.dob_student_container);
        Address = convertView.findViewById(R.id.addres_student_container);
        Fname.setText(fname);
        Semester.setText(semester);
        Number.setText(mobile);
        DOB.setText(dob);
        Address.setText(address);
        return convertView;
    }
}

