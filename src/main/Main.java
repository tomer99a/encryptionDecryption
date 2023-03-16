import com.fasterxml.jackson.databind.ObjectMapper;
import dirEncryption.AsyncDirectoryProcessor;
import dirEncryption.SyncDirectoryProcessor;
import encryption.IEncryptionAlgorithm;
import encryption.charAlgo.ShiftMultiplyEncryption;
import encryption.charAlgo.ShiftUpEncryption;
import encryption.generalsAlgo.DoubleEncryption;
import keys.DoubleKey;
import keys.NormalKey;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import schema.ProcessSettings;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    static final Logger logger = LogManager.getLogger(Main.class);

    private static void menu() {

        String fileName = "input_text";
        String basePath = "src" + File.separator + "main" + File.separator + "data" + File.separator;
        String originalPath = basePath + fileName + ".txt";
        String encryptedPath = basePath + fileName + "_encrypted.txt";
        String decryptedPath = basePath + fileName + "_decrypted.txt";
        String keyPath1 = basePath + "key1.txt";
        String keyPath2 = basePath + "key2.txt";
        DoubleKey doubleKey = new DoubleKey(keyPath1, keyPath2);

        String invalidChoiceErrorMessage = "You should write 1, 2 or 3 only!!!";
        boolean doneLoop = false;
        Scanner myScanner = new Scanner(System.in);

        IEncryptionAlgorithm<DoubleKey> iEncryptionAlgorithm = new DoubleEncryption(new ShiftMultiplyEncryption());

        FileEncryptor<DoubleKey> fileEncryptor = new FileEncryptor<>(iEncryptionAlgorithm);
        while (!doneLoop) {
            int choice;

            System.out.println("Hello user! please choose number:\n1 - encryption\n2 - decryption\n3 - exit");

            try {
                choice = Integer.parseInt(myScanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println(invalidChoiceErrorMessage);
                continue;
            }
            switch (choice) {
                case 1:
                    fileEncryptor.encrypt(originalPath, encryptedPath, doubleKey);
                    break;
                case 2:
                    fileEncryptor.decrypt(encryptedPath, decryptedPath, doubleKey);
                    break;
                case 3:
                    doneLoop = true;
                    break;
                default:
                    logger.info(invalidChoiceErrorMessage);
                    break;
            }
        }
    }

    private static void dirEncrypt() {
        String basePath = "src" + File.separator + "main" + File.separator + "data";
        String keyPath = basePath + File.separator + "key.txt";
        NormalKey normalKey = new NormalKey(keyPath);

        /*
        String keyPath1 = basePath + File.separator + "key1.txt";
        String keyPath2 = basePath + File.separator + "key2.txt";
        DoubleKey doubleKey = new DoubleKey(keyPath1, keyPath2);
         */

        String invalidChoiceErrorMessage = "You should write only number between 1 to 5!!!";
        boolean doneLoop = false;
        Scanner myScanner = new Scanner(System.in);

        while (!doneLoop) {
            int choice;

            System.out.println("Hello user! please choose number:" +
                    "\n1 - Encryption folder async (with threads)" +
                    "\n2 - Decryption folder async (with threads)" +
                    "\n3 - Encryption folder sync (without threads)" +
                    "\n4 - Encryption folder sync (without threads)" +
                    "\n5 - Exit");

            try {
                choice = Integer.parseInt(myScanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println(invalidChoiceErrorMessage);
                continue;
            }
            try {
                switch (choice) {
                    case 1:
                        new AsyncDirectoryProcessor<NormalKey>(basePath).encryptDir(new ShiftUpEncryption(), normalKey);
                        break;
                    case 2:
                        new AsyncDirectoryProcessor<NormalKey>(basePath).decryptDir(new ShiftUpEncryption(), normalKey);
                        break;
                    case 3:
                        new SyncDirectoryProcessor<NormalKey>(basePath).encryptDir(new ShiftUpEncryption(), normalKey);
                        break;
                    case 4:
                        new SyncDirectoryProcessor<NormalKey>(basePath).decryptDir(new ShiftUpEncryption(), normalKey);
                        break;
                    case 5:
                        doneLoop = true;
                        break;
                    default:
                        System.err.println(invalidChoiceErrorMessage);
                        break;
                }
            } catch (IOException | InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }

    private static void EncryptDecryptWithDataClass(ProcessSettings processData) {
        try {
            new AsyncDirectoryProcessor<NormalKey>(processData.getSourceDirectory()).encryptDir(processData.choseAlgo(), new NormalKey(processData.getKeyPath()));
            new AsyncDirectoryProcessor<NormalKey>(processData.getSourceDirectory()).decryptDir(processData.choseAlgo(), new NormalKey(processData.getKeyPath()));
        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
        }

    }

    private static void jaxbAndJsonMenu() {
        String invalidChoiceErrorMessage = "You should write only number between 1 to 3!!!";
        Scanner myScanner = new Scanner(System.in);

        while (true) {
            int choice;

            System.out.println("Hello user! please choose number:" +
                    "\n1 - Encrypt decrypt with XML data" +
                    "\n2 - Encrypt decrypt with JSON data" +
                    "\n3 - Exit");

            try {
                choice = Integer.parseInt(myScanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println(invalidChoiceErrorMessage);
                continue;
            }

            try {
                ProcessSettings processData;

                switch (choice) {
                    case 1:
                        processData = validatorXML();
                        EncryptDecryptWithDataClass(processData);
                        break;
                    case 2:
                        processData = useSchemaJSON();
                        EncryptDecryptWithDataClass(processData);
                        break;
                    case 3:
                        return;
                    default:
                        System.err.println(invalidChoiceErrorMessage);
                        break;
                }
            } catch (JAXBException | SAXException | IOException e) {
                // Should go here only if the xml or json didn't work.
                System.err.println("The data didn't loaded\nError message - " + e.getMessage());
            }
        }
    }

    private static void useXML() {
        try {
            File file = new File(String.valueOf(Paths.get("src", "main", "schema", "data.xml")));
            JAXBContext jaxbContext = JAXBContext.newInstance(ProcessSettings.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ProcessSettings process = (ProcessSettings) jaxbUnmarshaller.unmarshal(file);

            process.encrypt();
            process.decrypt();

        } catch (JAXBException | IOException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private static ProcessSettings validatorXML() throws JAXBException, SAXException {
        Path xmlPath = Paths.get("src", "main", "schema", "data.xml");
        Path xsdPath = Paths.get("src", "main", "schema", "schema.xsd");
        File xmlFile = new File(xmlPath.toString());
        File xsdFile = new File(xsdPath.toString());

        JAXBContext jaxbContext;

        //Get JAXBContext
        jaxbContext = JAXBContext.newInstance(ProcessSettings.class);

        //Create Unmarshaller
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        //Setup schema validator
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema processSchema = sf.newSchema(xsdFile);
        jaxbUnmarshaller.setSchema(processSchema);

        //Unmarshal xml file

        return (ProcessSettings) jaxbUnmarshaller.unmarshal(xmlFile);
    }

    private static ProcessSettings useSchemaJSON() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Path jsonPath = Paths.get("src", "main", "schema", "data.json");
        File jsonFile = new File(jsonPath.toString());

        return objectMapper.readValue(jsonFile, ProcessSettings.class);
    }

    public static void main(String[] args) {
        jaxbAndJsonMenu();
        System.out.println("Done program");
    }
}
