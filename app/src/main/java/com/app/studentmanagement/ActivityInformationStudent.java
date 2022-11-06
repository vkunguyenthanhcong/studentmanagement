package com.app.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityInformationStudent extends AppCompatActivity {

    TextView txtName, txtSex, txtStudentCode, txtBirthday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_student);

        txtBirthday = findViewById(R.id.txtstudentdateofbirth);
        txtStudentCode = findViewById(R.id.txtstudentcode);
        txtName = findViewById(R.id.txtstudentname);
        txtSex = findViewById(R.id.txtstudentsex);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String sex = intent.getStringExtra("sex");
        String code = intent.getStringExtra("code");
        String birthday = intent.getStringExtra("birthday");

        txtName.setText(name);
        txtSex.setText(sex);
        txtStudentCode.setText(code);
        txtBirthday.setText(birthday);

    }
}