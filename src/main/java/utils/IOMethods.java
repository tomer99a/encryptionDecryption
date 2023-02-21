package java.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import java.encryption.EncryptionAlgorithmInterface;
import static java.utils.GeneralMethods.scanLines;

public class IOMethods {
    /**
     * Scan the input file line by line and put the changed lines into the output line
     * @param inputPath path to input file
     * @param outputPath path to output file
     * @param encryptsDecrypt interface with the function to change the line
     * @param key key to usr to encrypt/decrypt
     */
    public static void scanAndSubmitFile(boolean encrypt, String inputPath, String outputPath, EncryptionAlgorithmInterface encryptsDecrypt, int key){
        try (Scanner sc = new Scanner(new FileInputStream(inputPath), String.valueOf(StandardCharsets.UTF_8))) {
            while (sc.hasNextLine())
                writeLine(outputPath, scanLines(encrypt, sc.nextLine(), encryptsDecrypt, key));

//             note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            System.err.println("failed to scan file");
        }
    }

    /**
     * Open file and append to in message
     * @param path path to file should be written.
     * @param line message to be written into file.
     */
    public static void writeLine(String path, String line) {
        try (Writer output = new BufferedWriter(new FileWriter(path, true))) {
            output.append(line);
        } catch (IOException e) {
            System.err.println("failed to write to file " + path);
        }
    }

    /**
     * Creat file at the given path and delete the exists if was one.
     * @param path file path and name
     */
    public static void createFile(String path){
        try {
            final File MY_OBJ = new File(path);
            if(MY_OBJ.exists())
                if(!MY_OBJ.delete())
                    throw new IOException("unable to delete existing file");
            if (!MY_OBJ.createNewFile())
                throw new IOException("failed to creat %s file " + path);

        } catch (IOException e) {
            System.err.print(e.getMessage());
        }
    }

    /**
     * Write the given message to the file at the given path
     * @param path file path and name
     * @param message message to write into path
     */
    public static void writeToFile(String path, String message){
        try (FileWriter myWriter = new FileWriter(path)) {
            myWriter.write(message);
        } catch (IOException e) {
            System.err.printf("failed to write to %s file", path);
        }
    }

    /**
     * Read all file from the path given.
     * @param path file path and name
     * @return string of the text to the given file combine and separated by \n char
     */
    public static String readFile(String path){
        StringBuilder txt = new StringBuilder();
        try (Scanner myReader = new Scanner(new File(path))) {
            while (myReader.hasNextLine()) {
                txt.append(myReader.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.err.printf("failed to read %s file", path);
        }
        return txt.toString();
    }

    /**
     * copy content of one file to another
     * @param originalPath file path to copy from
     * @param newPath file path to copy
     */
    public static void copyFile(String originalPath, String newPath)
    {
        File originalFile = new File(originalPath);
        File newFile = new File(newPath);

        try (FileInputStream in = new FileInputStream(originalFile); FileOutputStream out = new FileOutputStream(newFile)) {
            int tmpChar;
            while ((tmpChar = in.read()) != -1) {
                out.write(tmpChar);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static boolean compareTwoFiles(String path1, String path2){
//        if(path1.equals(path2))
//            return true;
        File file1 = new File(path1);
        File file2 = new File(path2);

        try (FileInputStream in1 = new FileInputStream(file1); FileInputStream in2 = new FileInputStream(file2)) {
            int tmpChar1, tmpChar2;

            while ((tmpChar1 = in1.read()) != -1) {
                tmpChar2 = in2.read();
                if(tmpChar2 == -1)
                    return false;
                if(tmpChar1 != tmpChar2)
                    return false;
            }
            tmpChar2 = in2.read();
            return tmpChar2 == -1;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
