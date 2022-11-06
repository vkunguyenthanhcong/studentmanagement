package com.app.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ActivitySubjectinformation extends AppCompatActivity {

    TextView edtTitle, edtCredit, edtTime, edtPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectinformation);

        edtTitle = findViewById(R.id.txtTitle);
        edtTime = findViewById(R.id.txttime);
        edtCredit = findViewById(R.id.txtcredit);
        edtPlace = findViewById(R.id.txtplace);

        Intent intent = getIntent();
        String title = getIntent().getStringExtra("title");
        int credit = intent.getIntExtra("credit", 0);
        String time = intent.getStringExtra("time");
        String place = intent.getStringExtra("place");

        edtTitle.setText(title);
        edtTime.setText(time);
        edtPlace.setText(place);
        edtCredit.setText(credit+"");
    }
}