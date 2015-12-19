
public interface EncryptionAlgorithm {

    char encryptChar(char ch, int key);

    char decryptChar(char ch, int key);

    int getKeyStrength();

}
