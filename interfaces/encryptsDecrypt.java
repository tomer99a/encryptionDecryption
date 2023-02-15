package encryptionDecryption.interfaces;

public interface encryptsDecrypt {
    void act();
    void getPaths();
    void mainAct();
    char handleCher(char c, int key, int start, int end);
}
