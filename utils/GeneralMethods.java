package encryptionDecryption.utils;

import encryptionDecryption.interfaces.encryptsDecrypt;

import java.io.File;
import java.util.Scanner;

public class GeneralMethods {
    /**
     * take the full path and enter only to the name some suffix
     * @param path file path and name
     * @param suffix string to add in the end of file name
     * @return new path with new file name
     */
    public static String addSuffixFileName(String path, String suffix){
        String newPath = path.substring(path.lastIndexOf("\\")+1, path.indexOf("."));
        newPath = path.substring(0, path.lastIndexOf("\\")+1) + newPath + "_" + suffix;
        newPath += path.substring(path.indexOf("."));
        return newPath;
    }

    public static String scanLines(String line, encryptsDecrypt encryptsDecrypt, int key){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char charToAdd = line.charAt(i);
            charToAdd = encryptsDecrypt.handleCher(charToAdd, key,'A', 'Z');
            charToAdd = encryptsDecrypt.handleCher(charToAdd, key,'a', 'z');
            str.append(charToAdd);
        }
        str.append("\n");
        return str.toString();
    }

    public static String pathFromUser(String fileRequired, String shouldContain){
        // TODO: delete the switch case!!
        String ans = "";
        switch (fileRequired) {
            case "encryption" -> ans = "src\\encryptionDecryption\\data\\input text_encrypted.txt";
            case "key" -> ans = "src\\encryptionDecryption\\data\\key.txt";
            case "input" -> ans = "src\\encryptionDecryption\\data\\input text.txt";
        }
        if(!ans.equals(""))
            return ans;

        Scanner myScanner = new Scanner(System.in);
        System.out.printf("Please enter the path to the %s source file", fileRequired);
        String path = myScanner.nextLine();  // Read user input
        while(!(new File(path).exists() && path.contains(shouldContain) && path.contains(".txt"))) {
            System.err.println("The given path is incorrect");
            System.out.printf("Please enter the path to the %s source file", fileRequired);
            path = myScanner.nextLine();
        }
        return path;
    }
}
