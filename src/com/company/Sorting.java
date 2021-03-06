package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Sorting {

    public static void userInput(String path){
        ArrayList<String> in = new ArrayList<String>();
        in.add("256");
        in.add("19");
        in.add("20");
        in.add("21");
        in.add("22");
        in.add("264");
        in.add("255");
        in.add("480");
        in.add("1080");
        in.add("720");
        in.add(".mkv");
        in.add(".mp4");
        in.add(".mpeg");
        in.add(".mpeg2");
        in.add("bluray");
        in.add("hdrip");
        in.add("hdcam");
        in.add("hdtv");
        in.add("4k");
        in.add("web");
        in.add(".avi");
        userInput(path,in);
    }
    public static void userInput(String path,ArrayList<String> in)
    {
        NewDir.buildNewFolder(path);
        Scanner scanner = new Scanner( System.in );
        System.out.println("Enter the file path: ");
        String dirPath = path; // Takes the directory path as the user input
        ArrayList<String>input = in;

        File folder = new File(dirPath);
        if(folder.isDirectory())
        {
            File[] fileList = folder.listFiles();

            Arrays.sort(fileList);

            System.out.println("\nTotal number of items present in the directory: " + fileList.length );


            // Lists only files since we have applied file filter
            for(File file:fileList)
            {
                String temp1 = file.getName().substring(0,index(input,file));
                char[]temp2 = temp1.toCharArray();
                String temp = "";
                String year = getYear(file.getName());
                for(int i=0;i<temp2.length;i++){
                    if(i !=temp2.length-1) {//for buildinf=g dir currectly
                        if (temp2[i] == '.' || temp2[i] == '-' || temp2[i] == '_' || temp2[i] == ')' || temp2[i] == '(' || temp2[i] == '*') {
                            temp += ' ';
                        } else {
                            temp += temp2[i];
                        }
                    }
                }
                temp += " " + year;
                    while (temp.endsWith(" ")){
                        temp = temp.substring(0,temp.length()-1);
                    }
                System.out.println(temp + "...............");
                File dir = new File(dirPath +"\\"+ temp);
                dir.mkdir();
                File destination = new File(dir.getAbsolutePath()+"\\"+file.getName());
                System.out.println(destination.getPath());
                if (!destination.exists()) {
                    if(file.renameTo(destination)){
                        file.delete();
                    }
                }
                else {
                    System.out.println("ERROR : "+file.getName());
                }
            }


        }
//        Object[] options = {"ok"};
//        JOptionPane.showOptionDialog(null, "Successfully Finished :)",
//                "The End :)", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    }
    private static ArrayList<String> input(){
        ArrayList<String>input = new ArrayList<String>();
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter your file filtering : file ends with \n(it build a folder from start name of file and ends with your input):");
        while (flag) {
        input.add(scanner.nextLine());
            System.out.printf("for ending yor input type---> END ");
            if(input.get(input.size()-1).equals("END"))
                flag = false;
        }
        return input;
    }
    private static int index(ArrayList<String> in, File file){
        int f=file.getName().length();
        for(int i=0;i<file.getName().length();i++){
            boolean flag = false;
            if(i>0){
                String temp=file.getName().toLowerCase();
                for(int j=0;j<in.size();j++) {
                    if (temp.substring(0, i).endsWith(in.get(j))) {
                        f = i - in.get(j).length();
                       flag = true;
                       break;
                    }
                }
            }
            if(flag)
                break;
        }
        return f;
    }
    private static String getYear(String in){
        char [] temp = in.toCharArray();
        if(in.contains("20") || in.contains("19") || in.contains("21") || in.contains("22") ) {
            for (int i = 2; i < in.length() && i+2 < in.length(); i++) {
                String q = "" + temp[0] + temp[1];
                if (q.equals("20") || q.equals("19") || q.equals("21") || q.equals("22")) {
                    return "" + q + temp[i + 1] + temp[i + 2];
                }
            }
        }
        return "";
    }
}
