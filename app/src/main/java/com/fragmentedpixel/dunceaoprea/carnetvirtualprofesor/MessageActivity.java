package com.fragmentedpixel.dunceaoprea.carnetvirtualprofesor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        TextView header = (TextView) findViewById(R.id.message_header_textView);
        header.setText("Mesaj catre " + Teacher.teacher.selectedClass);

        Button sendButton = (Button) findViewById(R.id.message_send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SendMessage();
            }
        });
    }

    private void SendMessage()
    {
        EditText editTextMessage = (EditText) findViewById(R.id.messge_editText);
        String message = editTextMessage.getText().toString();

        Toast.makeText(MessageActivity.this, "Mesajul: " + message + " catre clasa " + Teacher.teacher.selectedClass, Toast.LENGTH_LONG).show();
    }
}
