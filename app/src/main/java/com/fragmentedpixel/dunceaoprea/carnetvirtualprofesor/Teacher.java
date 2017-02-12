package com.fragmentedpixel.dunceaoprea.carnetvirtualprofesor;

import java.util.ArrayList;

/**
 * Created by oalex on 2017-02-11 .
 */

public class Teacher
{
    public static Teacher teacher;
    public static ArrayList<Student> students = new ArrayList<>();

    public ArrayList<String> classes;
    public String selectedClass;

    public Teacher(ArrayList<String> classes)
    {
        this.classes = classes;

        teacher = this;
    }

    public static void ResetStudentsList()
    {
        students = new ArrayList<>();
    }

}
