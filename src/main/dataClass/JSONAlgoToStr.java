package dataClass;

import com.fasterxml.jackson.databind.util.StdConverter;
import encryption.IEncryptionAlgorithm;
import keys.NormalKey;

import static dataClass.AdapterUtils.encryptToString;

public class JSONAlgoToStr extends StdConverter<IEncryptionAlgorithm<NormalKey>, String> {
    @Override
    public String convert(IEncryptionAlgorithm<NormalKey> algo) {
        return encryptToString(algo);
    }
}
