
public class ShiftUpEncryption extends EncryptionAlgorithmSuper
        implements EncryptionAlgorithm {

    public ShiftUpEncryption(){
        charEncryptOp = new CharEncryptor();
        charDecryptOp = new CharDecryptor();
    }

    private class CharEncryptor implements CharIntOperator{
        public char operate(char ch,int key) throws InvalidEncryptionKeyException{
            checkKey(key);
            return (char)((ch+key)%MAX_KEY_VAL);
        }
    }

    public class CharDecryptor implements CharIntOperator{
        public char operate(char ch,int key) throws InvalidEncryptionKeyException{
            checkKey(key);
            return (char)((ch-key)%MAX_KEY_VAL);
        }
    }
}
