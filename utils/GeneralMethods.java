package encryptionDecryption.utils;

import encryptionDecryption.general.encryptsDecrypt;

import java.io.File;
import java.util.Scanner;

import static encryptionDecryption.utils.IOMethods.readFile;

public class GeneralMethods {
    /**
     * add suffix only to the name of the file and return the hole path
     * take the full path and enter only to the name some suffix
     * @param suffix string to add in the end of file name
     * @param file file object to handle
     * @return new path that the name of file have new suffix.
     */
    public static String addSuffixFileName(File file, String suffix){
        String filename = file.getName().substring(0, file.getName().indexOf("."));
        String fileType = file.getPath().substring(file.getPath().indexOf("."));

        return file.getParent() + "\\" + filename + "_" + suffix + fileType;
    }

    /**
     * take string and do action on it char by char
     * @param line string to change
     * @param encryptsDecrypt interface with the method to do the action
     * @param key of the action to use
     * @return change string
     */
    public static String scanLines(String line, encryptsDecrypt encryptsDecrypt, int key){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char charToAdd = line.charAt(i);
            charToAdd = encryptsDecrypt.handleCher(charToAdd, key);
            str.append(charToAdd);
        }
        str.append("\n");
        return str.toString();
    }

    /**
     * ask path to file from user and check if it is good one.
     * wait until the given path is good.
     * @param fileRequired name of the file
     * @param shouldContain string that the path must include, if not have to include can get empty string
     * @return proper path from user
     */
    public static String pathFromUser(String fileRequired, String shouldContain){
        Scanner myScanner = new Scanner(System.in);
        System.out.printf("Please enter the path to the %s source file", fileRequired);
        String path = myScanner.nextLine();  // Read user input
        File file = new File(path);
        while(!(file.exists() && path.contains(shouldContain) && file.isFile())) {
            System.err.println("The given path is incorrect");
            System.out.printf("Please enter the path to the %s source file", fileRequired);
            path = myScanner.nextLine();
        }
        return path;
    }

    /**
     * return if the char is capitals or not and the range of the ascii code
     * @param c char in range
     * @return range
     */
    public static int[] getRange(char c){
        if('A' <= c && c <= 'Z')
            return new int[]{'A', 'Z'};
        else if('a' <= c && c <= 'z')
            return new int[]{'a', 'z'};
        return new int[]{-1};
    }

    public static int getKeyFromFile(String keyPath){
        try{
            String keyStr = readFile(keyPath);
            if(keyStr.indexOf('\n') != -1)
                keyStr = keyStr.substring(0, keyStr.indexOf('\n'));

            return Integer.parseInt(keyStr);
        } catch (NumberFormatException e) {
            System.err.println("The key file doesn't contain number");
            return -1;
        }
    }
}
