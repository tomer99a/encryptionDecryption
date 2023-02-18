package encryptionDecryption.utils;

import encryptionDecryption.general.encryptsDecrypt;

import java.io.File;

import static encryptionDecryption.utils.IOMethods.readFile;

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

    public static int fixNotGoodChars(int c){
        //TODO: Need to find a way to do it without 'magic numbers'.
        if (c == 141) {
            return 136;
        }
        if (c == 143) {
            return 135;
        }
        if (c == 144) {
            return 134;
        }
        if (c == 157) {
            return 133;
        }
        if (c == 160) {
            return 132;
        }
        if (c == 173) {
            return 131;
        }
        if (c == 136) {
            return 141;
        }
        if (c == 135) {
            return 143;
        }
        if (c == 134) {
            return 144;
        }
        if (c == 133) {
            return 157;
        }
        if (c == 132) {
            return 160;
        }
        if (c == 131) {
            return 173;
        }
        return c;
    }
}
