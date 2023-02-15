package encryption;

import static utils.generalMethods.*;


public class EncryptionAlgorithm {

    /**
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @param start start of the ASCII sequence
     * @param end end of the ASCII sequence
     * @return the encryption char that you're looking for
     */
    public static char encryptChar(char c, int key, int start, int end){
        if((int) c >= start && end >= (int) c){
            if((int) c + key >= end)
                return (char) ((int) c + key - 25);
            else
                return (char) ((int) c + key);
        }
        return c;
    }
//    public static char encryptChar(char c, int key, int start, int end){
//        int newAscii = (int) c + key;
//        return start <= (int) c && (int) c <= end ? (newAscii <= end ? (char) (newAscii+25) : (char) newAscii) : c;
//    }

    public static void encryption(){
        System.out.println("Please enter the path to the input source file");
        String path = "src\\data\\input text.txt";

        String txt = readFile(path);

//        int key = (int) (Math.random()*10); // get random number 1 to 10;
        int key = 3;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < txt.length(); i++) {
            char charToAdd = txt.charAt(i);
            charToAdd = encryptChar(charToAdd, key, 65, 90);
            charToAdd = encryptChar(charToAdd, key, 97, 122);
            str.append(charToAdd);
        }
        String encryptedPath = addSuffixFileName(path, "encrypted");
        String keyPath = path.substring(0, path.lastIndexOf("\\")+1) + "key.txt";

        creatFile(keyPath);
        creatFile(encryptedPath);

        writeToFile(keyPath, Integer.toString(key));
        writeToFile(encryptedPath, str.toString());

        System.out.printf("Location of the files are -\nencrypted - %s\nkey - %s%n", encryptedPath, keyPath);
    }
}
