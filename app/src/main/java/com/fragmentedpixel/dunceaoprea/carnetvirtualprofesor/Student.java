package com.fragmentedpixel.dunceaoprea.carnetvirtualprofesor;

import java.util.ArrayList;

/**
 * Created by oalex on 2017-02-11 .
 */

public class Student {

    public String stName;
    public String stForname;
    public int stID;

    public Student(String stName, String stForname, int stID) {
        this.stName = stName;
        this.stForname = stForname;
        this.stID = stID;

        Teacher.students.add(this);
    }
}
