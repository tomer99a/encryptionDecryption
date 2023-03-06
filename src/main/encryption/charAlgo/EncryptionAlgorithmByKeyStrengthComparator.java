package encryption.charAlgo;

import keys.NormalKey;

import java.util.Comparator;

public class EncryptionAlgorithmByKeyStrengthComparator implements Comparator<CharEncryptionAlgorithmAbstract<NormalKey>> {
    /**
     * Compare two encryption's algorithms by there key strength.
     *
     * @param algo1 first algorithms to compare
     * @param algo2 second algorithms to compare
     * @return negative number if the second algo is better and otherwise if the first is better.
     */
    @Override
    public int compare(final CharEncryptionAlgorithmAbstract algo1, final CharEncryptionAlgorithmAbstract algo2) {
        return algo1.getKeyStrength() - algo2.getKeyStrength();
    }
}
