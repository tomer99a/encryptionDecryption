package utils;

import encryption.CharEncryptionAlgorithmInterface;

import java.io.File;

import static utils.IOMethods.readFile;

public class GeneralMethods {
    /**
     * Take string and do action on it char by char
     * @param line string to change
     * @param encryptsDecrypt interface with the method to do the action
     * @param key of the action to use
     * @return changed string
     */
    public static String scanLines(boolean encrypt, String line, CharEncryptionAlgorithmInterface encryptsDecrypt, int key){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if(encrypt)
                str.append(encryptsDecrypt.encryptChar(line.charAt(i), key));
            else
                str.append(encryptsDecrypt.decryptChar(line.charAt(i), key));
        }
        return str.toString();
    }

    /**
     * Return if the char is capitals or not and the range of the ascii code
     * @param c char in range
     * @return range
     */
    public static int getRange(char c){
        if(Character.isLetter(c)) {
            return Character.isUpperCase(c) ? 'A' : 'a';
        }
        return -1;
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
