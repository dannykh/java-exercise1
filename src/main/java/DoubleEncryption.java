
public class DoubleEncryption {
    EncryptionAlgorithm encryptionAlg;

    private DoubleEncryption() {
    }

    public DoubleEncryption(EncryptionAlgorithm alg) {
        encryptionAlg = alg;

    }

    public char encryptChar(char ch, int key1, int key2)
            throws InvalidEncryptionKeyException {
        return encryptionAlg.encryptChar(
                encryptionAlg.encryptChar(ch, key1), key2);
    }

    public char decryptChar(char ch, int key1, int key2)
            throws InvalidEncryptionKeyException {
        return encryptionAlg.decryptChar(
                encryptionAlg.decryptChar(ch, key1), key2);
    }

    public String encryptString(String str, int key1, int key2)
            throws InvalidEncryptionKeyException {
        return encryptionAlg.encryptString(
                encryptionAlg.encryptString(str, key1), key2);
    }

    public String decryptString(String str, int key1, int key2)
            throws InvalidEncryptionKeyException {
        return encryptionAlg.decryptString(
                encryptionAlg.decryptString(str, key1), key2);
    }

    /*
    public class CharEncryptor implements CharIntOperator {
        public char operate(char ch, int key1,int key2) throws
        InvalidEncryptionKeyException{
            ch=encryptionAlg.encryptChar(ch,key1);
            return encryptionAlg.encryptChar(ch,key2);
        }
        public char operate(char ch, int key) throws
        InvalidEncryptionKeyException{
            return operate(ch,key,key);
        }
    }

    public class CharDecryptor implements CharIntOperator {
        public char operate(char ch, int key1,int key2) throws
        InvalidEncryptionKeyException{
            ch=encryptionAlg.decryptChar(ch, key1);
            return encryptionAlg.decryptChar(ch, key2);
        }
        public char operate(char ch, int key) throws
        InvalidEncryptionKeyException{
            return operate(ch,key,key);
        }
    }

   */
}
