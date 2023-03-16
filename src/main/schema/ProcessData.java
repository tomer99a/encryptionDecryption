package schema;

import encryption.IEncryptionAlgorithm;
import encryption.charAlgo.ShiftMultiplyEncryption;
import encryption.charAlgo.ShiftUpEncryption;
import encryption.charAlgo.XorEncryption;
import keys.NormalKey;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class ProcessData {
    private String algorithm;
    private String keyPath;
    private String sourceDirectory;
    private String sourceFileName;

    public ProcessData() {

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

    @Override
    public String toString() {
        return "ProcessData{" +
                "algorithm='" + algorithm + '\'' +
                ", keyPath='" + keyPath + '\'' +
                ", sourceDirectory='" + sourceDirectory + '\'' +
                ", sourceFileName='" + sourceFileName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProcessData)) return false;
        ProcessData that = (ProcessData) o;
        return algorithm.equals(that.algorithm) && keyPath.equals(that.keyPath) && sourceDirectory.equals(that.sourceDirectory) && sourceFileName.equals(that.sourceFileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(algorithm, keyPath, sourceDirectory, sourceFileName);
    }

    public IEncryptionAlgorithm<NormalKey> choseAlgo() {
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
}
