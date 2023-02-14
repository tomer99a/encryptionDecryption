package com.company;

import static com.company.generalMethods.*;


public class EncryptionAlgorithm {
    public EncryptionAlgorithm() {
    }


    public static void encryption(){
        System.out.println("Please enter the path to the input source file");
        System.out.println("C:\\Users\\Tomer\\Desktop\\encryptor\\src\\com\\company\\input text.txt");
        String path = "C:\\Users\\Tomer\\Desktop\\encryptor\\src\\com\\company\\input text.txt";

        String txt = readFile(path);

        int n = (int) (Math.random()*10); // get random number 1 to 10;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < txt.length(); i++) {
            str.append(txt.charAt(i));
            if(Character.isLetter(txt.charAt(i))){
                str.append(n);
            }
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
