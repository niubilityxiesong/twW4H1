package com.tw;

public class StudentData {
    int id;
    String name = new String();
    int mathgread, Chinesegread, englishgread, programminggread;

    public StudentData(int idnum, String buff, int math, int Chinese, int english, int programming){

        id = idnum;
        name = buff;
        mathgread = math;
        Chinesegread = Chinese;
        englishgread = english;
        programminggread = programming;
    }


}
