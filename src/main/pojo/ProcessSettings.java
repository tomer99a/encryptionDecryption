package pojo;

import dirEncryption.AsyncDirectoryProcessor;
import encryption.IEncryptionAlgorithm;
import encryption.charAlgo.ShiftMultiplyEncryption;
import encryption.charAlgo.ShiftUpEncryption;
import encryption.charAlgo.XorEncryption;
import keys.NormalKey;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;

@XmlRootElement
public class ProcessSettings {
    private String algorithm;
    private String keyPath;
    private String sourceDirectory;
    private String sourceFileName;

    public ProcessSettings() {

    }

    public ProcessSettings(String algorithm, String keyPath, String sourceDirectory, String sourceFileName) {
        super();
        this.algorithm = algorithm;
        this.keyPath = keyPath;
        this.sourceDirectory = sourceDirectory;
        this.sourceFileName = sourceFileName;
    }

    @XmlElement
    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    @XmlElement
    public String getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }

    @XmlElement
    public String getSourceDirectory() {
        return sourceDirectory;
    }

    public void setSourceDirectory(String sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

    @XmlElement
    public String getSourceFileName() {
        return sourceFileName;
    }

    public void setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    private IEncryptionAlgorithm<NormalKey> choseAlgo() {
        IEncryptionAlgorithm<NormalKey> encryptionAlgorithm;
        if (algorithm.equals("ShiftMultiply")) {
            encryptionAlgorithm = new ShiftMultiplyEncryption();
        } else if (algorithm.equals("ShiftUp")) {
            encryptionAlgorithm = new ShiftUpEncryption();
        } else {
            encryptionAlgorithm = new XorEncryption();
        }
        return encryptionAlgorithm;
    }

    public void encrypt() throws IOException, InterruptedException {
        IEncryptionAlgorithm<NormalKey> encryptionAlgorithm = choseAlgo();
        new AsyncDirectoryProcessor<NormalKey>(sourceDirectory).encryptDir(encryptionAlgorithm, new NormalKey(keyPath));
    }

    public void decrypt() throws IOException, InterruptedException {
        IEncryptionAlgorithm<NormalKey> encryptionAlgorithm = choseAlgo();
        new AsyncDirectoryProcessor<NormalKey>(sourceDirectory).decryptDir(encryptionAlgorithm, new NormalKey(keyPath));

    }
}
