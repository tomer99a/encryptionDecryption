package encryptionDecryption;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class generalMethods {
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
            System.out.println("An error occurred.");
            e.printStackTrace();
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
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     *
     * @param path
     * @return string of the text to the given file combine and separated by \n
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
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return txt.toString();
    }

    /**
     * take the full path and enter only to the name some suffix
     * @param path
     * @param suffix
     * @return
     */
    public static String addSuffixFileName(String path, String suffix){
        String new_path = path.substring(path.lastIndexOf("\\")+1, path.indexOf("."));
        new_path = path.substring(0, path.lastIndexOf("\\")+1) + new_path + "_" + suffix;
        new_path += path.substring(path.indexOf("."));
        return new_path;
    }

}
