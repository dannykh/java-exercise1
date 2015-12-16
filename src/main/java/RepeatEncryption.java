
public class RepeatEncryption extends EncryptionAlgorithmSuper
        implements EncryptionAlgorithm{
    private EncryptionAlgorithm encryptionAlg;
    private int nRep;

    private RepeatEncryption(){}
    public RepeatEncryption(EncryptionAlgorithm alg, int n) throws IllegalArgumentException{
        if (n<0){
            throw new IllegalArgumentException("Number of repetitions must be >= 0");
        }
        nRep=n;
        encryptionAlg=alg;
        charEncryptOp= new CharEncryptor();
        charDecryptOp = new CharDecryptor();
    }

    public class CharEncryptor implements CharIntOperator{
        public char operate(char ch, int key) throws InvalidEncryptionKeyException {
            checkKey(key);
            for (int i = 0; i < nRep; i++) {
                ch = encryptionAlg.encryptChar(ch, key);
            }
            return ch;
        }
    }

    public class CharDecryptor implements CharIntOperator{
        public char operate(char ch, int key) throws InvalidEncryptionKeyException {
            checkKey(key);
            for (int i = 0; i < nRep; i++) {
                ch = encryptionAlg.encryptChar(ch, key);
            }
            return ch;
        }
    }

}
