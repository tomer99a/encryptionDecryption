package encryptionDecryption.interfaces;

public interface encryptsDecrypt {
    void act();
    void setPath();
    char handleCher(char c, int key, int start, int end);
}
