package encryptionDecryption.utils;

import encryptionDecryption.encryption.EncryptionAlgorithm;
import encryptionDecryption.interfaces.encryptsDecrypt;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class IOMethods {
    public static void scanAndSubmitFile(String inputPath, String outputPath, encryptsDecrypt encryptsDecrypt, int key){
        try (Scanner sc = new Scanner(new FileInputStream(inputPath), StandardCharsets.UTF_8)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < line.length(); i++) {
                    char charToAdd = line.charAt(i);
                    charToAdd = encryptsDecrypt.handleCher(charToAdd, key,'A', 'Z');
                    charToAdd = encryptsDecrypt.handleCher(charToAdd, key,'a', 'z');
                    str.append(charToAdd);
                }
                str.append("\n");
                writeLine(outputPath, str.toString());
            }
//             note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            System.err.println("failed to scan file");
            e.printStackTrace();
        }
    }

    public static void writeLine(String path, String line) throws IOException {
        Writer output = null;
        try{
            output = new BufferedWriter(new FileWriter(path, true));
            output.append(line);
        } catch (IOException e) {
            System.err.println("failed to write to file " + path);
        } finally {
            assert output != null;
            output.close();
        }
    }

    /**
     * creat file at the given path
     * @param path
     */
    public static void creatFile(String path){
        try {
            File myObj = new File(path);
            if(myObj.exists())
                myObj.delete();
            if (!myObj.createNewFile())
                System.err.println("File already exists.");
        } catch (IOException e) {
            System.err.printf("failed to creat %s file", path);
        }
    }

    /**
     * write the given message to the file at the given path
     * @param path
     * @param message
     */
    public static void writeToFile(String path, String message){
        try {
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(message);
            myWriter.close();
        } catch (IOException e) {
            System.err.printf("failed to write to %s file", path);
        }
    }

    /**
     *
     * @param path
     * @return string of the text to the given file combine and separated by \n char
     */
    public static String readFile(String path){
        StringBuilder txt = new StringBuilder();
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                txt.append(myReader.nextLine()).append("\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.err.printf("failed to read %s file", path);
        }
        return txt.toString();
    }
}
