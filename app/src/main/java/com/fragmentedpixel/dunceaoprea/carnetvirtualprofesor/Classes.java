package com.fragmentedpixel.dunceaoprea.carnetvirtualprofesor;

import java.util.ArrayList;

/**
 * Created by elev on 2/13/2017.
 */

public class Classes {
    public Integer CID;
    public String CName;
    public ArrayList<Student> students;

    public Classes (Integer CID,String CName, ArrayList<Student> students)
    {
        this.CID = CID;
        this.CName = CName;
        this.students = students;
    }
}
