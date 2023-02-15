package encryptionDecryption.utils;

import encryptionDecryption.general.encryptsDecrypt;

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
}
