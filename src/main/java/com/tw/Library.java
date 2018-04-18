package com.tw;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {

    String inputchar = new String();
    int inputnum;
    Scanner inputall = new Scanner(System.in);
    List<StudentData> stdlist = new ArrayList<>();

    public Library(){
        inputchar = null;
        inputnum = -1;
    }

    void PrintMainWindows() {
        System.out.println("1,添加学生");
        System.out.println("2,生成成绩单");
        System.out.println("3,推出");
        System.out.println("请输入你的选择（1 ~ 3）:");
        inputnum = Integer.parseInt(inputall.next());
        MakeAChoice();
    }

    public boolean MakeAChoice() {

        if(inputnum == -1){
            return false;
        }

        switch (inputnum){
            case 1:
                addnewstudent();
                inputnum = -1;
                PrintMainWindows();
                break;

            case 2:
                GetAList();
                inputchar = null;
                PrintMainWindows();
                break;

            case 3:
                return true;

            default:
                GteSomeMessage(inputnum);
                return false;


        }
        return true;
    }

    public void addnewstudent(){
        System.out.println("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：");
        inputchar = inputall.nextLine();

        if(inputchar.length() < 2){
            System.out.println("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：");
            addnewstudent();
        }

        int id = -1;
        String name = new String();
        name = null;
        int mathgread = -1, Chinesegread = -1, englishgread = -1, programminggread = -1;
        StudentData tempstd;
        int inputlocation = 0;
        int subjectlocation = -1;
        String temparray = new String();

        for(int i = 0; i < inputchar.length(); i++){

            if(inputchar.charAt(i) == ','){
                if(name == null){
                    name = inputchar.substring(0, i - 1);
                    inputlocation = i;
                    continue;
                }
                if(id == -1){
                    id = Integer.parseInt(inputchar.substring(inputlocation + 2, i - 1));
                    inputlocation = i;
                    continue;
                }
            }

            if(inputchar.charAt(i) == ':'){
                temparray = inputchar.substring(inputlocation + 2, i);
                subjectlocation = FindSubject(temparray);
                if(subjectlocation != -1){
                    while(i < inputchar.length() && inputchar.charAt(i) != ','){
                        i++;
                    }
                    if(i != inputchar.length()){
                        temparray = inputchar.substring(inputlocation + 2, i - 1);
                        switch (subjectlocation){
                            case 0:
                                mathgread = Integer.parseInt(temparray);
                                break;
                            case 1:
                                Chinesegread = Integer.parseInt(temparray);
                                break;
                            case 2:
                                englishgread = Integer.parseInt(temparray);
                            case 3:
                                programminggread = Integer.parseInt(temparray);
                                break;
                        }
                    }
                }
            }
        }

        tempstd = new StudentData(id, name, mathgread, Chinesegread, englishgread, programminggread);
        stdlist.add(tempstd);
        System.out.print("学生");
        System.out.print(name);
        System.out.println("的成绩被添加");
    }

    public int FindSubject(String array){
        List<String> subject = new ArrayList<>();
        int i;
        subject.add("数学");
        subject.add("语文");
        subject.add("英语");
        subject.add("编程");

        for(i = 0; i < subject.size(); i++){
            if(array.equals(subject.get(i))){
                return i;
            }
        }
        return -1;
    }

    public boolean GetAList(){
        inputchar = inputall.nextLine();

        if(inputchar.length() == 0 || inputchar.charAt(0) > '9' || inputchar.charAt(0) < '0'){
            System.out.println("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
        }

        System.out.println("成绩单");
        System.out.println("姓名|数学|语文|英语|编程|平均分|总分");
        System.out.println("========================");


        return true;
    }

    public void GteSomeMessage(int errornum){
        System.out.print(errornum);
        System.out.println(" is not a legal number. Please enter: 1,2 or 3");
    }

}
