package com.app.studentmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import com.app.studentmanagement.adapter.adapterstudent;
import com.app.studentmanagement.database.database;
import com.app.studentmanagement.model.Student;

import java.util.ArrayList;

public class ActivityStudent extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewstudent;

    ArrayList<Student> ArrayListStudent;
    com.app.studentmanagement.database.database database;
    com.app.studentmanagement.adapter.adapterstudent adapterstudent;

    int id_subject = 0;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        toolbar = findViewById(R.id.toolbarstudent);
        listViewstudent = findViewById(R.id.listviewStudent);

        Intent intent = getIntent();
        id_subject = intent.getIntExtra("id_subject", 0);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new database(this);
        ArrayListStudent = new ArrayList<>();
        ArrayListStudent.clear();

        Cursor cursor = database.getDataStudent(id_subject);
        while (cursor.moveToNext()){
            int id_sub = cursor.getInt(5);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String sex = cursor.getString(2);
            String code = cursor.getString(3);
            String birthday = cursor.getString(4);

            ArrayListStudent.add(new Student(id, name, sex, code, birthday, id_sub));
        }

        adapterstudent = new adapterstudent(ActivityStudent.this, ArrayListStudent);
        listViewstudent.setAdapter(adapterstudent);
        cursor.moveToFirst();
        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuaddstudent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuaddstudent:

                Intent intent = new Intent(ActivityStudent.this, ActivityAddStudent.class);
                intent.putExtra("id_subject", id_subject);
                startActivity(intent);
                break;
            default:
                Intent i = new Intent(ActivityStudent.this, ActivitySubject.class);
                startActivity(i);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        counter++;
        if(counter>=1){
            Intent intent = new Intent(ActivityStudent.this, ActivitySubject.class);
            startActivity(intent);
            finish();
        }
    }

    public void infomation(final int pos){
        Cursor cursor = database.getDataStudent(id_subject);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            if(id == pos){
                Intent intent = new Intent(ActivityStudent.this, ActivityInformationStudent.class);
                intent.putExtra("id", pos);
                String name = cursor.getString(1);
                String sex = cursor.getString(2);
                String code = cursor.getString(3);
                String birthday = cursor.getString(4);
                int id_subject = cursor.getInt(5);

                intent.putExtra("name", name);
                intent.putExtra("sex", sex);
                intent.putExtra("code", code);
                intent.putExtra("birthday", birthday);
                intent.putExtra("id_subject", id_subject);

                startActivity(intent);
            }
        }
        cursor.close();
    }

    public void update(final int id_student){
        Cursor cursor = database.getDataStudent(id_subject);

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            if(id == id_student){
                Intent intent = new Intent(ActivityStudent.this, ActivityUpdateStudent.class);
                intent.putExtra("id", id_student);
                String name = cursor.getString(1);
                String sex = cursor.getString(2);
                String code = cursor.getString(3);
                String birthday = cursor.getString(4);
                int id_subject = cursor.getInt(5);

                intent.putExtra("name", name);
                intent.putExtra("sex", sex);
                intent.putExtra("code", code);
                intent.putExtra("birthday", birthday);
                intent.putExtra("id_subject", id_subject);

                startActivity(intent);
            }
        }
        cursor.close();
    }
    public void delete(final int id_student){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogstudentdelete);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btndeletestudentyes);
        Button btnNo = dialog.findViewById(R.id.btndeletestudentno);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.DeleteStudent(id_student);
                Intent intent = new Intent(ActivityStudent.this, ActivityStudent.class);
                intent.putExtra("id_subject", id_subject);
                startActivity(intent);
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}