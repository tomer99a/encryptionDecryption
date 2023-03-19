package dataClass;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import encryption.IEncryptionAlgorithm;
import keys.NormalKey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static dataClass.AdapterUtils.stringToEncrypt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ProcessDataTest {
    @ParameterizedTest
    @ValueSource(strings = {"ShiftMultiply", "ShiftUp", "Xor"})
    @DisplayName("creat data class from and to json file")
    void json(String algoName) {
        IEncryptionAlgorithm<NormalKey> algo = stringToEncrypt(algoName);
        ProcessData data = new ProcessData(algo, "8", "7", "6");
        ObjectMapper mapper = new ObjectMapper();

        //Data class to json
        String jsonStr = "";
        try {
            jsonStr = mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            fail("The data class didn't tern into json\n" + e.getMessage());
        }
        String expectedJson = String.format("{\"algorithm\":\"%s\",\"keyPath\":\"\",\"sourceDirectory\":\"\",\"sourceFileName\":\"\"}", algoName);
        assertEquals(expectedJson, jsonStr);

        //json to data class
        ProcessData dataFromJson = null;
        try {
            dataFromJson = mapper.readValue(jsonStr, ProcessData.class);
        } catch (IOException e) {
            fail("The json didn't tern into data class\n" + e.getMessage());
        }
        assertEquals(data, dataFromJson);
    }

    @ParameterizedTest
    @ValueSource(strings = {"ShiftMultiply", "ShiftUp", "Xor"})
    @DisplayName("creat data class from and to json file")
    void xml(String algoName) {
        File tmpXML = new File("tmp.xml");
        JAXBContext jaxbContext = null;
        Marshaller marshallerObj = null;
        try {
            tmpXML = File.createTempFile("temp", ".xml");
            jaxbContext = JAXBContext.newInstance(ProcessData.class);
            marshallerObj = jaxbContext.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (IOException | JAXBException e) {
            fail(e.getMessage());
        }
        IEncryptionAlgorithm<NormalKey> algo = stringToEncrypt(algoName);
        ProcessData data = new ProcessData(algo, "3", "4", "5");


        //Data class to xml
        try {
            marshallerObj.marshal(data, new FileOutputStream(tmpXML));
        } catch (FileNotFoundException | JAXBException e) {
            fail("The data class didn't tern into xml\n" + e.getMessage());
        }

        //xml to data class
        Unmarshaller jaxbUnmarshaller;
        ProcessData dataFromXML = null;
        try {
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            //Setup schema validator
            dataFromXML = (ProcessData) jaxbUnmarshaller.unmarshal(tmpXML);
            tmpXML.delete();

        } catch (JAXBException e) {
            fail("The xml didn't tern into data class\n" + e.getMessage());
        }
        assertEquals(data, dataFromXML);

    }
}