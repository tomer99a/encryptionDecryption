package dataClass;

import encryption.IEncryptionAlgorithm;
import keys.NormalKey;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import static dataClass.AdapterUtils.encryptToString;
import static dataClass.AdapterUtils.stringToEncrypt;

public class XMLAlgoAdapter extends XmlAdapter<String, IEncryptionAlgorithm<NormalKey>> {
    @Override
    public IEncryptionAlgorithm<NormalKey> unmarshal(String algoName) throws IllegalArgumentException {
        return stringToEncrypt(algoName);
    }

    @Override
    public String marshal(IEncryptionAlgorithm<NormalKey> algo) throws IllegalArgumentException {
        return encryptToString(algo);
    }
}
