package com.company;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Sorting {

    public static void userInput()
    {
        Scanner scanner = new Scanner( System.in );
        System.out.println("Enter the file path: ");
        String dirPath = scanner.nextLine(); // Takes the directory path as the user input
        ArrayList<String>input = input();

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
                for(int i=0;i<temp2.length;i++){
                    if(i !=temp2.length-1) {//for buildinf=g dir currectly
                        if (temp2[i] == '.' || temp2[i] == '-' || temp2[i] == '_' || temp2[i] == ')' || temp2[i] == '(' || temp2[i] == '*') {
                            temp += ' ';
                        } else {
                            temp += temp2[i];
                        }
                    }
                }
                System.out.println(temp + "...............");
                File dir = new File(dirPath +"\\"+ temp);
                dir.mkdir();
                File destination = new File(dir.getAbsolutePath()+"\\"+file.getName());
                System.out.println(dir.getPath());
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
                String temp=file.getName();
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
}
