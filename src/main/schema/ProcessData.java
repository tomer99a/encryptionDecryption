package schema;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import encryption.IEncryptionAlgorithm;
import keys.NormalKey;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Objects;

@XmlRootElement
public class ProcessData {
    private IEncryptionAlgorithm<NormalKey> algorithm;
    private String keyPath;
    private String sourceDirectory;
    private String sourceFileName;

    @XmlJavaTypeAdapter(XMLAlgoAdapter.class)
    @XmlElement(name = "algorithm", required = true)
    @JsonDeserialize(converter = JSONAlgoAdapter.class)
    public IEncryptionAlgorithm<NormalKey> getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(IEncryptionAlgorithm<NormalKey> algorithm) {
        this.algorithm = algorithm;
    }

    @XmlElement(name = "keyPath", required = true)
    public String getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }

    @XmlElement(name = "sourceDirectory", required = true)
    public String getSourceDirectory() {
        return sourceDirectory;
    }

    public void setSourceDirectory(String sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

    @XmlElement(name = "sourceFileName", required = true)
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
}
