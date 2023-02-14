package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class generalMethods {
    public static void creatFile(String path){
        try {
            File myObj = new File(path);
            if(myObj.exists())
                myObj.delete();
            if (!myObj.createNewFile())
                System.out.println("File already exists.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeToFile(String path, String message){
        try {
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(message);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String readFile(String path){
        StringBuilder txt = new StringBuilder();
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                txt.append(myReader.nextLine()).append("\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return txt.toString();
    }

    public static String addSuffixFileName(String path, String suffix){
        String new_path = path.substring(path.lastIndexOf("\\")+1, path.indexOf("."));
        new_path = path.substring(0, path.lastIndexOf("\\")+1) + new_path + "_" + suffix;
        new_path += path.substring(path.indexOf("."));
        return new_path;
    }

}
