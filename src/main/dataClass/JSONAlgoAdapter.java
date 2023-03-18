package dataClass;

import com.fasterxml.jackson.databind.util.StdConverter;
import encryption.IEncryptionAlgorithm;
import keys.NormalKey;

import static dataClass.AdapterUtils.stringToEncrypt;

public class JSONAlgoAdapter extends StdConverter<String, IEncryptionAlgorithm<NormalKey>> {
    @Override
    public IEncryptionAlgorithm<NormalKey> convert(String algoName) {
        return stringToEncrypt(algoName);
    }
}
