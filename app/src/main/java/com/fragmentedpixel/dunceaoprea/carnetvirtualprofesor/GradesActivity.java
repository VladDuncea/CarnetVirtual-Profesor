package com.fragmentedpixel.dunceaoprea.carnetvirtualprofesor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GradesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        SetUp();
        SetStudentsSpinner();

        Button submiteButton = (Button) findViewById(R.id.grade_submit_button);
        submiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Submit();
            }
        });
    }

    private void SetUp()
    {
        TextView header = (TextView) findViewById(R.id.classHeader_textView);
        header.setText(Teacher.teacher.selectedClass.CName);

        TextView date = (TextView) findViewById(R.id.date_textView);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/YY", Locale.getDefault());
        date.setText(df.format(Calendar.getInstance().getTime()));
    }

    private void SetStudentsSpinner()
    {
        ArrayList<String> students = new ArrayList<>();

        for(Student s: Teacher.teacher.selectedClass.students)
            students.add(s.stName + " " + s.stForname);

        Spinner studentsSpinner = (Spinner) findViewById(R.id.students_Spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, students);
        studentsSpinner.setAdapter(adapter);
    }

    private void Submit()
    {
        //TODO: pls add some request to the server. P.S: be polite with the server he might get angry

        Spinner studentsSpinner = (Spinner) findViewById(R.id.students_Spinner);
        int index = studentsSpinner.getSelectedItemPosition();

        /* Informatii utile*/
        String result = Teacher.teacher.selectedClass.students.get(index).stName + " " + Teacher.teacher.selectedClass.students.get(index).stForname;
        int STID = Teacher.teacher.selectedClass.students.get(index).stID;
        Boolean eTeza = ((CheckBox) findViewById(R.id.teza_checkBox)).isChecked();
        Date dateNow =  Calendar.getInstance().getTime();
        String nota = ((EditText) findViewById(R.id.nota_editText)).getText().toString();

        //Output
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/YY", Locale.getDefault());
        result += " " + df.format(dateNow) + " " + nota;
        Toast.makeText(GradesActivity.this, result, Toast.LENGTH_LONG).show();
    }
}
