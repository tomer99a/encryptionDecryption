package encryptionDecryption;

import static encryptionDecryption.generalMethods.*;


public class EncryptionAlgorithm {
    public EncryptionAlgorithm() {
    }

    public static char encryptChar(char c, int n, int start, int end){
        if((int) c >= start && end >= (int) c){
            if((int) c + n >= end)
                return (char) ((int) c + n - 25);
            else
                return (char) ((int) c + n);
        }
        return c;
    }


    public static void encryption(){
        System.out.println("Please enter the path to the input source file");
        System.out.println("C:\\Users\\Tomer\\Desktop\\encryptionDecryption\\src\\encryptionDecryption\\input text.txt");
        String path = "C:\\Users\\Tomer\\Desktop\\encryptionDecryption\\src\\encryptionDecryption\\input text.txt";

        String txt = readFile(path);

        int n = (int) (Math.random()*10); // get random number 1 to 10;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < txt.length(); i++) {
            char charToAdd = txt.charAt(i);
            charToAdd = encryptChar(charToAdd, n, 65, 90);
            charToAdd = encryptChar(charToAdd, n, 97, 122);
            str.append(charToAdd);
        }
        String encrypted_path = addSuffixFileName(path, "encrypted");
        String key_path = path.substring(0, path.lastIndexOf("\\")+1) + "key.txt";

        creatFile(key_path);
        creatFile(encrypted_path);

        writeToFile(key_path, Integer.toString(n));
        writeToFile(encrypted_path, str.toString());

        System.out.printf("Location of the files are -\nencrypted - %s\nkey - %s%n", encrypted_path, key_path);
    }
}
