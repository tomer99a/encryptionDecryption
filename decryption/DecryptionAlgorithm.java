package encryptionDecryption.decryption;

import encryptionDecryption.interfaces.encryptsDecrypt;

import static encryptionDecryption.utils.GeneralMethods.*;
import static encryptionDecryption.utils.IOMethods.*;

public class DecryptionAlgorithm implements encryptsDecrypt {
    private String encryptionPath;
    private String keyPath;
    private String decryptedPath;

    public void act(){
        getPaths();
        final int key = Integer.parseInt(readFile(keyPath).charAt(0)+"");
        creatFile(decryptedPath);
        scanAndSubmitFile(encryptionPath, decryptedPath, this, key);
        System.out.println("Location of the decrypted file is - " + decryptedPath);
    }

    public void getPaths(){
//        Scanner myScanner = new Scanner(System.in);
        System.out.println("Please enter the path to the encryption source file");
//        encryptionPath = myScanner.nextLine();  // Read user input
        encryptionPath = "src\\encryptionDecryption\\data\\input text_encrypted.txt";

        System.out.println("Please enter the path to the key file");
//        String keyPath = myScanner.nextLine();  // Read user input
        keyPath = "src\\encryptionDecryption\\data\\key.txt";

        String originalPath = encryptionPath.substring(0, encryptionPath.indexOf("_")) + encryptionPath.substring(encryptionPath.indexOf("."));
        decryptedPath = addSuffixFileName(originalPath, "decrypted");
    }

    /**
     *
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @param start start of the ASCII sequence
     * @param end end of the ASCII sequence
     * @return the decryption char that you're looking for
     */
    public char handleCher(char c, int key, int start, int end){
        if((int) c >= start && end >= (int) c)
            if((int) c - key < start)
                return (char) ((int) c - key + end - start);
            else
                return (char) ((int) c - key);
        return c;
    }
}
