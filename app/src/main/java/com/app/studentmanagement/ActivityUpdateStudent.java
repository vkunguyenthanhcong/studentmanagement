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

public class ActivityUpdateStudent extends AppCompatActivity {

    EditText edtUpdateName, edtUpdteStudentCode, edtUpdateBirthday;
    RadioButton updatemale, updatefemale;
    Button buttonUpdateStudent;
    com.app.studentmanagement.database.database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        edtUpdateBirthday =  findViewById(R.id.updateStudentBirthday);
        edtUpdateName = findViewById(R.id.updateStudentName);
        edtUpdteStudentCode = findViewById(R.id.updateStudentCode);

        updatefemale = findViewById(R.id.updatebuttomFemale);
        updatemale = findViewById(R.id.updatebuttomMale);
        buttonUpdateStudent = findViewById(R.id.buttonUpdateStudent);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String name = intent.getStringExtra("name");
        String sex = intent.getStringExtra("sex");
        String code = intent.getStringExtra("code");
        String birthday = intent.getStringExtra("birthday");
        int id_subject = intent.getIntExtra("id_subject", 0);

        edtUpdateName.setText(name);
        edtUpdateBirthday.setText(birthday);
        edtUpdteStudentCode.setText(code);

        if (sex.equals("Male")){
            updatemale.setChecked(true);
            updatefemale.setChecked(false);
        }else{
            updatefemale.setChecked(true);
            updatemale.setChecked(false);
        }
        database = new database(this);

        buttonUpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUpdate(id, id_subject);
            }
        });
    }
    private void DialogUpdate(int id, int id_subject){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogupdatestudent);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.updateYes);
        Button btnNo = dialog.findViewById(R.id.updateNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtUpdateName.getText().toString().trim();
                String code = edtUpdteStudentCode.getText().toString().trim();
                String birthday = edtUpdateBirthday.getText().toString().trim();

                Student student = createstudent();
                if (name.equals("") || code.equals("") || birthday.equals("")){
                    Toast.makeText(ActivityUpdateStudent.this, "Did not enter enough information", Toast.LENGTH_SHORT).show();
                }else{
                    database.UpdateStudent(student, id);
                    Intent intent = new Intent(ActivityUpdateStudent.this, ActivityStudent.class);
                    intent.putExtra("id_subject", id_subject);
                    startActivity(intent);
                    Toast.makeText(ActivityUpdateStudent.this, "more success", Toast.LENGTH_SHORT).show();
                }
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
    private Student createstudent(){
        String name = edtUpdateName.getText().toString().trim();
        String code = edtUpdteStudentCode.getText().toString().trim();
        String birthday = edtUpdateBirthday.getText().toString().trim();
        String sex = "";
        if (updatemale.isChecked()){
            sex = "Male";
        }else{
            sex = "Female";
        }

        Student student = new Student(name, sex, code, birthday);
        return student;
    }
}