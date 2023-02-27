package encryption.charAlgo;

import java.util.Comparator;

public class EncryptionAlgorithmByKeyStrengthComparator implements Comparator<CharEncryptionAlgorithmAbstract>{
    @Override
    public int compare(CharEncryptionAlgorithmAbstract algo1, CharEncryptionAlgorithmAbstract algo2) {
        return algo1.getKeyStrength() - algo2.getKeyStrength();
    }
}
