package com.app.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.studentmanagement.database.database;
import com.app.studentmanagement.model.Subject;

public class ActivityUpdateSubject extends AppCompatActivity {

    EditText edtUpdateTitle, edtUpdateCredit, edtUpdateTime, edtUpdatePlace;
    Button btnUpdateSubject;

    com.app.studentmanagement.database.database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subject);

        edtUpdateCredit = findViewById(R.id.edtUpdateSubjectCredit);
        edtUpdatePlace = findViewById(R.id.edtUpdateSubjectPlace);
        edtUpdateTime = findViewById(R.id.edtUpdateSubjectTime);
        edtUpdateTitle = findViewById(R.id.edtUpdateSubjectTitle);
        btnUpdateSubject = findViewById(R.id.buttonUpdateSubject);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String title = intent.getStringExtra("title");
        int credit = intent.getIntExtra("credit", 0);
        String time = intent.getStringExtra("time");
        String place = intent.getStringExtra("place");

        edtUpdateTitle.setText(title);
        edtUpdateTime.setText(time);
        edtUpdateCredit.setText(credit+"");
        edtUpdatePlace.setText(place);

        database = new database(this);
        btnUpdateSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUpdate(id);
            }
        });
    }
    private void DialogUpdate(int id){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogupdate);
        dialog.setCanceledOnTouchOutside(false);
        Button btnyes = dialog.findViewById(R.id.buttomYesup);
        Button btnno = dialog.findViewById(R.id.buttomNoup);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subjecttitle = edtUpdateTitle.getText().toString().trim();
                String credit = edtUpdateCredit.getText().toString().trim();
                String time = edtUpdateTime.getText().toString().trim();
                String place = edtUpdatePlace.getText().toString().trim();

                if (subjecttitle.equals("") || credit.equals("") || time.equals("") || place.equals("")){
                    Toast.makeText(ActivityUpdateSubject.this, "Did you enter enough information", Toast.LENGTH_SHORT).show();
                }else{
                    Subject subject = updatesubject();

                    database.UpdateSubject(subject, id);
                    Intent intent = new Intent(ActivityUpdateSubject.this, ActivitySubject.class);
                    startActivity(intent);
                    Toast.makeText(ActivityUpdateSubject.this, "more success", Toast.LENGTH_SHORT).show();

                }
            }
        });
        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    private Subject updatesubject(){
        String subjecttitle = edtUpdateTitle.getText().toString().trim();
        int credit = Integer.parseInt(edtUpdateCredit.getText().toString().trim());
        String time = edtUpdateTime.getText().toString().trim();
        String place = edtUpdatePlace.getText().toString().trim();

        Subject subject = new Subject(subjecttitle, credit, time, place);
        return subject;
    }
}