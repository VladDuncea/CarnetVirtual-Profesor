package com.fragmentedpixel.dunceaoprea.carnetvirtualprofesor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.awt.font.TextAttribute;
import java.sql.BatchUpdateException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PopulateList();
        LinkButtons();
    }

    private void LinkButtons()
    {
        Button chatButton = (Button) findViewById(R.id.catalog_button);
        Button gradeButton = (Button) findViewById(R.id.grades_button);
        Button presenceButton = (Button) findViewById(R.id.presences_button);
        Button messageButton = (Button) findViewById(R.id.write_message_button);

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
                startActivity(intent);
            }
        });

        gradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GradesActivity.class);
                startActivity(intent);
            }
        });

        presenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PresencesActivity.class);
                startActivity(intent);
            }
        });

        messageButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, MessageActivity.class);
            startActivity(intent);
        }
        });


    }

    private void PopulateList()
    {

        final Spinner classesSpinner = (Spinner) findViewById(R.id.classes_spinner);

        ArrayList<String> arraySpinner = new ArrayList<>();
        ArrayList<String> classesList = new ArrayList<>();

        for(Classes c : Teacher.teacher.classes)
            classesList.add(c.CName);

        for (String c : classesList)
                arraySpinner.add(c);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arraySpinner);
        classesSpinner.setAdapter(adapter);

        classesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {
            String selectedClassName = classesSpinner.getSelectedItem().toString();
            for(Classes c : Teacher.teacher.classes)
                if(c.CName.equals(selectedClassName))
                    Teacher.teacher.selectedClass = c;

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent)
        {
            Toast.makeText(MainActivity.this, "Please select a class", Toast.LENGTH_SHORT).show();
        }
    });
    }
}
