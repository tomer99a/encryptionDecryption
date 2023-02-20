package main.java.utils;

import main.java.encryption.EncryptionAlgorithmInterface;

import java.io.File;

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
    public static String scanLines(boolean encrypt, String line, EncryptionAlgorithmInterface encryptsDecrypt, int key){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if(encrypt)
                str.append(encryptsDecrypt.encryptChar(line.charAt(i), key));
            else
                str.append(encryptsDecrypt.decryptChar(line.charAt(i), key));
        }
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


}
