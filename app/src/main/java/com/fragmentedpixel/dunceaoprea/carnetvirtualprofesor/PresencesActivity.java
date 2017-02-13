package com.fragmentedpixel.dunceaoprea.carnetvirtualprofesor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PresencesActivity extends AppCompatActivity {


    ArrayList<String> studentsName = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presences);

        TextView classText = (TextView) findViewById(R.id.class_text);
        classText.setText(Teacher.teacher.selectedClass.CName);

        for(Student s : Teacher.teacher.selectedClass.students)
            studentsName.add(s.stName + " " + s.stForname);

        PopulateList();
        ListView studentsList = (ListView) findViewById(R.id.students_list);
        studentsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                studentsName.remove(position);
                PopulateList();
                Presence(Teacher.teacher.selectedClass.students.get(position).stID);
            }
        });
    }

    private void PopulateList()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, studentsName);
        ListView studentsList = (ListView) findViewById(R.id.students_list);
        studentsList.setAdapter(adapter);
    }

    private void Presence(int STID)
    {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/YY", Locale.getDefault());

        Toast.makeText(PresencesActivity.this, "Absenta la index: " + STID + " la data: " + df.format(date), Toast.LENGTH_LONG).show();
    }
}


