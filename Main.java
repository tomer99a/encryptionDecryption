package encryptionDecryption;

import encryptionDecryption.decryption.DecryptionAlgorithm;
import encryptionDecryption.encryption.EncryptionAlgorithm;

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
                    new EncryptionAlgorithm().encryption();
                else if (ans == 2)
                    new DecryptionAlgorithm().decryption();
                else
                    throw new Exception("You should write 1 or 2 only");
                break;
            } catch (Exception e) {
                System.out.println("You should write 1 or 2 only!!!");
            }
        }
    }

    public static void main(String[] args) {
        new EncryptionAlgorithm().encryption();
        System.out.println("------------");
        new DecryptionAlgorithm().decryption();
//        menu();

    }
}
