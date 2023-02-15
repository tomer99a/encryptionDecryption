package encryptionDecryption.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class IOMethods {
    /**
     * creat file at the given path
     * @param path
     */
    public static void creatFile(String path){
        try {
            File myObj = new File(path);
            if(myObj.exists())
                myObj.delete();
            if (!myObj.createNewFile())
                System.out.println("File already exists.");
        } catch (IOException e) {
            System.out.printf("failed to creat %s file", path);
        }
    }

    /**
     * write the given message to the file at the given path
     * @param path
     * @param message
     */
    public static void writeToFile(String path, String message){
        try {
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(message);
            myWriter.close();
        } catch (IOException e) {
            System.out.printf("failed to write to %s file", path);
        }
    }

    /**
     *
     * @param path
     * @return string of the text to the given file combine and separated by \n char
     */
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
            System.out.printf("failed to read %s file", path);
        }
        return txt.toString();
    }
}
