package com.fragmentedpixel.dunceaoprea.carnetvirtualprofesor;

import android.app.DatePickerDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MessageActivity extends AppCompatActivity {


    private EditText edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        TextView header = (TextView) findViewById(R.id.message_header_textView);
        header.setText("Mesaj catre " + Teacher.teacher.selectedClass.CName);

        Button sendButton = (Button) findViewById(R.id.message_send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SendMessage();
            }
        });

        SpinnerSetUp();
        edittext = (EditText) findViewById(R.id.date_editText);
        SetEditText();
    }

    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void SetEditText()
    {

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MessageActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

        edittext.setText(sdf.format(myCalendar.getTime()));
    }


    private void SpinnerSetUp()
    {
        Spinner messageTypeSpinner = (Spinner) findViewById(R.id.messageType_spinner);

        ArrayList<String> options = new ArrayList<>();
        options.add("Mesaj simplu");
        options.add("Test");
        options.add("Teza");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        messageTypeSpinner.setAdapter(adapter);
    }

    // TODO Oprea: Adauga un expire date la mesaj si tipul mesajului(1-simplu,2-test,3-teza)

    private void SendMessage()
    {
        EditText editTextMessage = (EditText) findViewById(R.id.messge_editText);
        final String message = editTextMessage.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    if(!jsonResponse.getBoolean("success"))
                    {
                        AlertDialog.Builder alert = new AlertDialog.Builder(MessageActivity.this);
                        alert.setMessage("Maintenance").setNegativeButton("Inapoi",null).create().show();
                    }
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){

                        Toast.makeText(MessageActivity.this,"Mesaj trimis.",Toast.LENGTH_LONG).show();

                    }
                    else{
                        AlertDialog.Builder alert = new AlertDialog.Builder(MessageActivity.this);
                        alert.setMessage("Eroare").setNegativeButton("Inapoi",null).create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        String CID = Teacher.teacher.selectedClass.CID.toString();
        String TID = Teacher.teacher.TID;
        String Name = Teacher.teacher.Name;

        _Chat_Upload chat_Request = new _Chat_Upload(message,CID,TID,Name,"1","1",responseListener);
        RequestQueue chat_Queue = Volley.newRequestQueue(MessageActivity.this);
        chat_Queue.add(chat_Request);


    }
}
