
class InvalidEncryptionKeyException extends RuntimeException{
    public InvalidEncryptionKeyException(){super();}
    public InvalidEncryptionKeyException(String message){super(message);}
}

public interface EncryptionAlgorithm {

    char encryptChar(char ch, int key) throws InvalidEncryptionKeyException;
    char decryptChar(char ch, int key) throws InvalidEncryptionKeyException;
    String encryptString(String str,int key) throws InvalidEncryptionKeyException;
    String decryptString(String str,int key) throws InvalidEncryptionKeyException;
    int getKeyStrength();

}
