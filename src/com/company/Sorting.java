package com.company;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Scanner;

public class Sorting {

    public void userInput()
    {
        Scanner scanner = new Scanner( System.in );
        System.out.println("Enter the file path: ");
        String dirPath = scanner.nextLine(); // Takes the directory path as the user input

        File folder = new File(dirPath);
        if(folder.isDirectory())
        {
            File[] fileList = folder.listFiles();

            Arrays.sort(fileList);

            System.out.println("\nTotal number of items present in the directory: " + fileList.length );


            // Lists only files since we have applied file filter
            for(File file:fileList)
            {
                int f=file.getName().length();
               for(int i=0;i<file.getName().length();i++){
                   if(i>0){
                       String temp=file.getName();
                       if(temp.substring(0,i).endsWith("20") || temp.substring(0,i).endsWith("19") || temp.substring(0,i).endsWith(".m")){
                           f=i-2;
                           break;
                       }
                   }
               }
               String temp = file.getName().substring(0,f);
                System.out.println(temp);
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
}
