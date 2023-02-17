package encryptionDecryption.encryption;

import java.util.Random;

import static encryptionDecryption.utils.GeneralMethods.getKeyFromFile;

public class DoubleEncryption extends EncryptionAlgorithmAbstract{
    public DoubleEncryption() {
        super("Double");
    }

    @Override
    public void act(){
        EncryptionAlgorithmInterface algo = chooseAlgo();
        algo.act();
//        final int key1 = getKeyFromFile();
    }

    @Override
    public char handleCher(char c, int key) {
        return 'T';
    }

    private EncryptionAlgorithmInterface chooseAlgo(){
        if(new Random().nextInt(2) == 0)
            return new ShiftUpEncryption();
        return new ShiftMultiplyEncryption();

    }
}
