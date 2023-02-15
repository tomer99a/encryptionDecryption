import decryption.DecryptionAlgorithm;
import encryption.EncryptionAlgorithm;

import java.util.Scanner;

public class Main {

    public static void menu(){
        Scanner myScanner = new Scanner(System.in);
        while (true){
            System.out.println("Hello user!\nif you want to Encryption press 1 and if you want to chose Decryption press 2");
            String userAns = myScanner.nextLine();  // Read user input
            try{
                int ans = Integer.parseInt(userAns);
                if(ans == 1)
                    EncryptionAlgorithm.encryption();
                else if (ans == 2)
                    DecryptionAlgorithm.decryption();
                else
                    throw new Exception("You should write 1 or 2 only");
                break;
            } catch (Exception e) {
                System.out.println("You should write 1 or 2 only!!!");
            }
        }
    }

    public static void main(String[] args) {
        EncryptionAlgorithm.encryption();
        System.out.println("------------");
        DecryptionAlgorithm.decryption();
//        int start = 65;
//        int end = 90;
//        int key = 3;
//        char c = 'I';
//
//        int newAscii = (int) c - key;
//        char eee = newAscii < 25 ? (char) (newAscii+25) : (char) newAscii;
//
//        System.out.println(((int) c + key - start) % (end - start));
//
//        char enc = (char) ((((int) c - start + key) % (end-start)) + start);
//
//        System.out.println(enc);
//
//        System.out.println((char) ((((int) enc - 2*start - key + end) % (end-start)) + start));
//        System.out.println((90-65.-3) % 25 + start);
//        menu();

    }
}
