package com.tw;

public class StudentData {
    int id;
    String name = new String();
    double mathgread, Chinesegread, englishgread, programminggread;

    public StudentData(int idnum, String buff, double math, double Chinese, double english, double programming){

        id = idnum;
        name = buff;
        mathgread = math;
        Chinesegread = Chinese;
        englishgread = english;
        programminggread = programming;
    }


}
