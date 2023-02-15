package encryptionDecryption.interfaces;

public interface encryptsDecrypt {
    void act();
    void getPaths();
    char handleCher(char c, int key, int start, int end);
}
