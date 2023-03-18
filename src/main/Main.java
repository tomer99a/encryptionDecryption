import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
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
import schema.ProcessData;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;

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

    private static void EncryptDecryptWithDataClass(ProcessData processData) {
        NormalKey normalKey = new NormalKey(processData.getKeyPath());
        String path = processData.getSourceDirectory();
        try {
            new AsyncDirectoryProcessor<NormalKey>(path).encryptDir(processData.getAlgorithm(), normalKey);
            new AsyncDirectoryProcessor<NormalKey>(path).decryptDir(processData.getAlgorithm(), normalKey);
        } catch (IOException | InterruptedException e) {
            logger.warn("The encryption or decryption fails.");
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
                ProcessData processData;

                switch (choice) {
                    case 1:
                        processData = validatorXML();
                        EncryptDecryptWithDataClass(processData);
                        break;
                    case 2:
                        processData = useJSONSchema();
                        EncryptDecryptWithDataClass(processData);
                        break;
                    case 3:
                        return;
                    default:
                        System.err.println(invalidChoiceErrorMessage);
                        break;
                }
            } catch (IOException | JAXBException e) {
                logger.error("The data didn't loaded. Error message -\n" + e.getMessage());
            } catch (SAXException e) {
                logger.error("The given schema isn't good. Error message -\n" + e.getMessage());
            }
            break;
        }
    }

    private static ProcessData validatorXML() throws JAXBException, SAXException {
        Path xmlPath = Paths.get("src", "main", "resources", "data.xml");
        Path xsdPath = Paths.get("src", "main", "resources", "schema.xsd");
        File xmlFile = new File(xmlPath.toString());
        File xsdFile = new File(xsdPath.toString());

        JAXBContext jaxbContext = JAXBContext.newInstance(ProcessData.class);

        //Create Unmarshaller
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        //Setup schema validator
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(xsdFile);
        jaxbUnmarshaller.setSchema(schema);

        //Unmarshal xml file and return the data class
        return (ProcessData) jaxbUnmarshaller.unmarshal(xmlFile);
    }

    private static ProcessData useJSONSchema() throws IOException {
        // create instance of the ObjectMapper class
        ObjectMapper mapper = new ObjectMapper();

        // create an instance of the JsonSchemaFactory using version flag
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);

        // store the JSON data in InputStream
        Path jsonPath = Paths.get("src", "main", "resources", "data.json");
        Path schemaPath = Paths.get("src", "main", "resources", "schema.json");

        // read data from the stream and store it into JsonNode
        JsonNode jsonNode = mapper.readTree(new File(jsonPath.toString()));

        // get schema from the schemaStream and store it into JsonSchema
        InputStream schemaStream = new FileInputStream(schemaPath.toString());
        JsonSchema jsonSchema = schemaFactory.getSchema(schemaStream);
        schemaStream.close();

        // create set of validation message and store result in it
        Set<ValidationMessage> validationResult = jsonSchema.validate(jsonNode);

        // show the validation errors
        if (validationResult.isEmpty()) {
            // show custom message if there is no validation error
            return mapper.treeToValue(jsonNode, ProcessData.class);

        } else {
            // show all the validation error
            validationResult.forEach(vm -> logger.error(vm.getMessage()));
            throw new IOException("The json didn't fit to his schema");
        }
    }

    public static void main(String[] args) {
        jaxbAndJsonMenu();
        System.out.println("Done program");
    }
}
