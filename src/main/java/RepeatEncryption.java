
public class RepeatEncryption extends EncryptionAlgorithmBase {
    private EncryptionAlgorithm encryptionAlg;
    private int nRep;

    private RepeatEncryption() {
    }

    public RepeatEncryption(EncryptionAlgorithm alg, int n){
        if (n < 0) {
            throw new IllegalArgumentException("Number of repetitions must be >= 0");
        }
        nRep = n;
        encryptionAlg = alg;
        charEncryptor = (ch, key) -> {
            char res = ch;
            for (int i = 0; i < nRep; i++) {
                res = encryptionAlg.encryptChar(res, key);
            }
            return res;
        };
        charDecryptor = (ch, key) -> {
            char res = ch;
            for (int i = 0; i < nRep; i++) {
                res = encryptionAlg.decryptChar(res, key);
            }
            return res;
        };
    }

}
