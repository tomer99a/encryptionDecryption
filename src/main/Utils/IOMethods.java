package Utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import Encryption.CharAlgo.CharEncryptionAlgorithmAbstract;
import static Utils.GeneralMethods.scanLines;

public class IOMethods {
    /**
     * Scan the input file line by line and put the changed lines into the output line
     * @param inputPath path to input file
     * @param outputPath path to output file
     * @param encryptsDecrypt interface with the function to change the line
     * @param key key to usr to encrypt/decrypt
     */
    public static void scanAndSubmitFile(boolean encrypt, String inputPath, String outputPath, CharEncryptionAlgorithmAbstract encryptsDecrypt, int key) throws IOException {
        try (Scanner sc = new Scanner(new FileInputStream(inputPath), String.valueOf(StandardCharsets.UTF_8))) {
            while (sc.hasNextLine()){
                String lineToWrite = scanLines(encrypt, sc.nextLine(), encryptsDecrypt, key);
                if(sc.hasNextLine())
                    lineToWrite += System.lineSeparator();
                writeLine(outputPath, lineToWrite);
            }

             // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            throw new IOException("failed to scan file");
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
    public static void createFile(String path) throws IOException {
        final File myObj = new File(path);
        if(myObj.exists())
            if(!myObj.delete())
                throw new IOException("unable to delete existing file" + System.lineSeparator());
        if (!myObj.createNewFile())
            throw new IOException(System.lineSeparator() + "failed to creat %s file " + path + System.lineSeparator());
        }

    /**
     * Write the given message to the file at the given path
     * @param path file path and name
     * @param message message to write into path
     */
    public static void writeToFile(String path, String message) throws IOException {
        try (FileWriter myWriter = new FileWriter(path)) {
            myWriter.write(message);
        } catch (IOException e) {
            throw new IOException("failed to write to %s file " + path + System.lineSeparator());
        }
    }

    /**
     * Read all file from the path given.
     * @param path file path and name
     * @return string of the text to the given file combine and separated by \n char
     */
    public static String readFile(String path) throws IOException {
        StringBuilder txt = new StringBuilder();
        try (Scanner myReader = new Scanner(new File(path))) {
            while (myReader.hasNextLine()) {
                txt.append(myReader.nextLine()).append((char) 10);
            }
        } catch (FileNotFoundException e) {
            throw new IOException("failed to read " + path + " file" + System.lineSeparator());
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
}
