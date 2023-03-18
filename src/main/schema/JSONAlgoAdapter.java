package schema;

import com.fasterxml.jackson.databind.util.StdConverter;
import encryption.IEncryptionAlgorithm;
import keys.NormalKey;

import static schema.AdapterUtils.stringToEncrypt;

public class JSONAlgoAdapter extends StdConverter<String, IEncryptionAlgorithm<NormalKey>> {
    @Override
    public IEncryptionAlgorithm<NormalKey> convert(String algoName) {
        return stringToEncrypt(algoName);
    }
}
