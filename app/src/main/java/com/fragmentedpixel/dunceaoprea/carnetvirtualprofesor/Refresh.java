package com.fragmentedpixel.dunceaoprea.carnetvirtualprofesor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.util.Base64;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by vlad_ on 12.02.2017.
 */

public class Refresh {
    public static void LogIn(final Context context, final String Email, final String Password) {

        Response.Listener<String> loginListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    if(!jsonResponse.getBoolean("success"))
                    {
                        AlertDialog.Builder alert = new AlertDialog.Builder(context);
                        alert.setMessage("Maintenance").setNegativeButton("Inapoi",null).create().show();
                    }
                    boolean success = jsonResponse.getBoolean("success");
                    boolean is_email_right = jsonResponse.getBoolean("is_email_right");
                    boolean is_password_right = jsonResponse.getBoolean("is_password_right");
                    if(success){

                        if (!is_email_right)
                        {
                            AlertDialog.Builder alert = new AlertDialog.Builder(context);
                            alert.setMessage("Email-ul e gresit.").setNegativeButton("Inapoi",null).create().show();
                        }
                        else if (!is_password_right)
                        {
                            AlertDialog.Builder alert = new AlertDialog.Builder(context);
                            alert.setMessage("Parola e necorespunzatoare.").setNegativeButton("Inapoi",null).create().show();
                        }
                        else
                        {
                            context.startActivity(new Intent(context, MainActivity.class));


                            /*
                            String SName = jsonResponse.getString("SName");
                            String SAddress = jsonResponse.getString("SAddress");
                            String SPhone = jsonResponse.getString("SPhone");
                            String CName = jsonResponse.getString("CName");
                            String STName = jsonResponse.getString("STName");
                            String STFirstName = jsonResponse.getString("STFirstName");
                            String STSerialNr = jsonResponse.getString("STSerialNr");
                            String STAddress = jsonResponse.getString("STAddress");
                            String STPhone = jsonResponse.getString("STPhone");
                            Integer Grade_nr = jsonResponse.getInt("Grade_nr");
                            Integer Presence_nr = jsonResponse.getInt("Presence_nr");
                            Integer Chat_nr = jsonResponse.getInt("Chat_nr");




                            //new Student(SName,SAddress,SPhone,CName,STName,STFirstName,STPicture_bm,Email,Password, BSignature_bm,STSerialNr,STAddress,STPhone);
                            //Serialization.saveSerializable(context);
                            //context.startActivity(new Intent(context, Main.class));

                            for(int i=0;i<Presence_nr;i++)
                            {
                                JSONObject presence = jsonResponse.getJSONObject("Presence"+i);
                                String PDate = presence.getString("PDate");
                                DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
                                Date date = format.parse(PDate);
                                Boolean PValue = presence.getBoolean("PValue");
                                String SBName = presence.getString("SBName");

                                new Presences(date,PValue,SBName);
                            }

                            for(int i=0;i<Grade_nr;i++)
                            {
                                JSONObject grade = jsonResponse.getJSONObject("Grade"+i);
                                String GDate = grade.getString("GDate");
                                DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
                                Date date = format.parse(GDate);
                                Integer GValue = grade.getInt("GValue");
                                String SBName = grade.getString("SBName");
                                new Grades(date,GValue,SBName);
                            }

                            for(int i=0;i<Chat_nr;i++)
                            {
                                JSONObject chat = jsonResponse.getJSONObject("Chat"+i);

                                DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
                                String CHDate = chat.getString("CHDate");
                                String CHEDate = chat.getString("CHEDate");
                                Date chdate = format.parse(CHDate);
                                Date chedate = format.parse(CHEDate);
                                Integer CHType = chat.getInt("CHType");
                                String CHMessage = chat.getString("CHMessage");
                                String TName = chat.getString("TName");
                                new ChatMessage(chdate,chedate,CHMessage,TName,CHType);
                            }
                            */
                        }
                    }
                    else{
                        AlertDialog.Builder alert = new AlertDialog.Builder(context);
                        alert.setMessage("Ups.. S-a intamplat ceva neprevazut").setNegativeButton("Inapoi",null).create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        _Login_Request login_Request = new _Login_Request(Email,Password,loginListener);
        RequestQueue login_Queue = Volley.newRequestQueue(context);
        login_Queue.add(login_Request);


    }
}
