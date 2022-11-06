package com.app.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.app.studentmanagement.database.database;
import com.app.studentmanagement.model.Student;

public class ActivityAddStudent extends AppCompatActivity {

    Button buttonAddStudent;
    EditText edtTextStudentName, edtTextStudentCode, edtTextDateofbirth;
    RadioButton radiobuttonmale, radiobuttonfemale;
    com.app.studentmanagement.database.database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        buttonAddStudent = findViewById(R.id.buttonAddStudent);
        edtTextDateofbirth = findViewById(R.id.EditTextStudentBirthday);
        edtTextStudentCode = findViewById(R.id.EditTextStudentCode);
        edtTextStudentName = findViewById(R.id.EditTextStudentName);

        radiobuttonfemale = findViewById(R.id.radiobuttomFemale);
        radiobuttonmale = findViewById(R.id.radiobuttomMale);

        Intent intent = getIntent();
        int id_subject = intent.getIntExtra("id_subject", 0);

        database = new database(this);
        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAdd(id_subject);
            }
        });
    }
    private void DialogAdd(int id_subject){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogstudentadd);
        dialog.setCanceledOnTouchOutside(false);

        Button btnyes = dialog.findViewById(R.id.btnaddstudentyes);
        Button btnno = dialog.findViewById(R.id.btnaddstudentno);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtTextStudentName.getText().toString().trim();
                String code = edtTextStudentCode.getText().toString().trim();
                String birthday = edtTextDateofbirth.getText().toString().trim();
                String sex = "";
                if (radiobuttonmale.isChecked()){
                    sex = "Male";
                }else if (radiobuttonfemale.isChecked()){
                    sex = "Female";
                }
                if (name.equals("") || code.equals("") || sex.equals("") || birthday.equals("")){
                    Toast.makeText(ActivityAddStudent.this, "Did not enter enough information", Toast.LENGTH_SHORT).show();
                }else{
                    Student student = CreateStudent(id_subject);
                    database.AddStudent(student);
                    Intent intent = new Intent(ActivityAddStudent.this, ActivityStudent.class);
                    intent.putExtra("id_subject", id_subject);
                    startActivity(intent);
                    Toast.makeText(ActivityAddStudent.this, "more success", Toast.LENGTH_SHORT).show();
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
    private Student CreateStudent(int id_subject){
        String name = edtTextStudentName.getText().toString().trim();
        String code = edtTextStudentCode.getText().toString().trim();
        String birthday = edtTextDateofbirth.getText().toString().trim();
        String sex = "";
        if (radiobuttonmale.isChecked()){
            sex = "Male";
        }else if (radiobuttonfemale.isChecked()){
            sex = "Female";
        }

        Student student = new Student(name, sex, code, birthday, id_subject);
        return student;
    }
}