package main.java.utils;

import main.java.general.encryptsDecrypt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static main.java.utils.IOMethods.copyFile;
import static main.java.utils.IOMethods.readFile;

public class GeneralMethods {
    /**
     * Add suffix only to the file name from the full path
     * @param path original path
     * @param suffix thing to add at the end of the file name
     * @return path with changed name
     */
    public static String addSuffixToFileNameAtPath(String path, String suffix){
        File file = new File(path);
        String fileName = file.getName();
        return file.getParent() + "\\" + fileName.substring(0, fileName.lastIndexOf(".")) + suffix + fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * Take string and do action on it char by char
     * @param line string to change
     * @param encryptsDecrypt interface with the method to do the action
     * @param key of the action to use
     * @return changed string
     */
    public static String scanLines(String line, encryptsDecrypt encryptsDecrypt, int key){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < line.length(); i++)
            str.append(encryptsDecrypt.handleCher(line.charAt(i), key));
        str.append("\n");
        return str.toString();
    }

    /**
     * Return if the char is capitals or not and the range of the ascii code
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

    /**
     * Extract key value from file
     * @param keyPath the path to the file key
     * @return key value
     */
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

    public static void repeatAct(int n, String originalPath, String outputPath, String keyPath, encryptsDecrypt algo) throws IOException {
        // Create a temporary file
        final String tmpPath = Files.createTempFile("RepeatTmp", ".txt").toString();

        algo.act(originalPath, tmpPath, keyPath);
        for(int i=1; i < n; i++){
            algo.act(tmpPath, outputPath, keyPath);
            copyFile(outputPath, tmpPath);
        }

        if (!(new File(tmpPath).delete()))
            System.err.println("The tmp file didn't auto delete");
    }
}
