package com.tw;

import java.util.*;

public class Library {

    String inputchar = new String();
    int inputnum;
    List<StudentData> stdlist = new ArrayList<>();
    List<Double> stdsumlist = new ArrayList<>();

    public Library(){
        inputchar = null;
        inputnum = -1;
    }

    public void  PrintMainWindows() {

        Scanner inputall = new Scanner(System.in);

        System.out.println("1,添加学生");
        System.out.println("2,生成成绩单");
        System.out.println("3,退出");
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
                PrintMainWindows();


        }
        return true;
    }

    public void addnewstudent(){

        Scanner inputall = new Scanner(System.in);

        System.out.println("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：");
        inputchar = inputall.nextLine();

        if(inputchar.length() < 2){
            System.out.println("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：");
            addnewstudent();
        }

        int id = -1;
        String name = new String();
        name = null;
        double mathgread = -1.0, Chinesegread = -1.0, englishgread = -1.0, programminggread = -1.0;
        StudentData tempstd;
        int inputlocation = 0;
        int subjectlocation = -1;
        String temparray = new String();

        for(int i = 0; i < inputchar.length(); i++){

            if(inputchar.charAt(i) == ','){
                if(name == null){
                    name = inputchar.substring(0, i);
                    inputlocation = i;
                    continue;
                }
                if(id == -1){
                    id = Integer.parseInt(inputchar.substring(inputlocation + 2, i));
                    inputlocation = i;
                    continue;
                }
            }

            if(inputchar.charAt(i) == ':'){
                temparray = inputchar.substring(inputlocation + 2, i);
                inputlocation = i;
                subjectlocation = FindSubject(temparray);
                if(subjectlocation != -1){
                    while(i < inputchar.length() && inputchar.charAt(i) != ','){
                        i++;
                    }
                    if(i != inputchar.length() + 1){
                        temparray = inputchar.substring(inputlocation + 1, i);
                        inputlocation = i;
                        switch (subjectlocation){
                            case 0:
                                mathgread = Double.parseDouble(temparray);
                                break;
                            case 1:
                                Chinesegread = Double.parseDouble(temparray);
                                break;
                            case 2:
                                englishgread = Double.parseDouble(temparray);
                            case 3:
                                programminggread = Double.parseDouble(temparray);
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

        Scanner inputall = new Scanner(System.in);

        System.out.println("请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
        inputchar = inputall.nextLine();

        if(inputchar.length() == 0 || inputchar.charAt(0) > '9' || inputchar.charAt(0) < '0'){
            System.out.println("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
            GetAList();
        }

        System.out.println("成绩单");
        System.out.println("姓名|数学|语文|英语|编程|平均分|总分");
        System.out.println("========================");

        String temparray = new String();
        int idnumbers = -1;
        int idcount = 0;
        int idlocation = 0;

        for(int i = 0; i < inputchar.length(); i++){

            if(inputchar.charAt(i) == ' '){
                continue;
            }

            if(inputchar.charAt(i) == ',' || i == inputchar.length() - 1){
                if(i == 0 || i == inputchar.length() - 1){
                    temparray = inputchar.substring(idlocation, i + 1);
                }
                else {
                    temparray = inputchar.substring(idlocation, i);
                }
                idlocation = i + 2;
                idnumbers = Integer.parseInt(temparray);
                if(Findstudents(idnumbers)){
                    ++idcount;
                }
                idnumbers = -1;
            }
            temparray += inputchar.charAt(i);
        }

        double avg = 0.0;
        double medianum = 0.0;
        for(int i = 0; i < stdsumlist.size(); i++){
            avg += stdsumlist.get(i);
        }
        avg = avg / idcount;
        int media = stdsumlist.size() / 2;
        if(stdsumlist.size() % 2 == 0){
            medianum = (stdsumlist.get(media - 1) + stdsumlist.get(media)) / 2;
        }
        else {
            Collections.sort(stdsumlist);
            medianum = stdsumlist.get(media);
        }

        System.out.println("========================");
        System.out.print("全班总分平均数：");
        System.out.println(avg);
        System.out.print("全班总分中位数：");
        System.out.println(medianum);
        stdsumlist.clear();
        return true;
    }

    public boolean Findstudents(int idnumber){

        if(idnumber == -1){
            return false;
        }

        int stdsize = stdlist.size();
        int i;
        for(i = 0; i < stdsize; i++){
            if(stdlist.get(i).id == idnumber){
                PrintfGreadsList(stdlist.get(i));
                break;
            }
        }
        if(i == stdsize)
            return false;
        return true;
    }

    public void PrintfGreadsList(StudentData std){

        double sum = 0.0;
        double avg = 0.0;

        sum = std.Chinesegread + std.programminggread + std.englishgread + std.mathgread;
        avg = sum / 4;
        System.out.print(std.name);
        System.out.print('|');
        System.out.print(std.mathgread);
        System.out.print('|');
        System.out.print(std.Chinesegread);
        System.out.print('|');
        System.out.print(std.englishgread);
        System.out.print('|');
        System.out.print(std.programminggread);
        System.out.print('|');
        System.out.print(avg);
        System.out.print('|');
        System.out.println(sum);

        stdsumlist.add(sum);
    }

    public void GteSomeMessage(int errornum){
        System.out.print(errornum);
        System.out.println(" is not a legal number. Please enter: 1,2 or 3");
    }



}
