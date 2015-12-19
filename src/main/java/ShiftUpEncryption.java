
public class ShiftUpEncryption extends EncryptionAlgorithmBase {

    public ShiftUpEncryption() {
        charEncryptor = (ch, key) -> (char) ((ch + key) % MAX_KEY_VAL);
        charDecryptor = (ch, key) -> (char) ((ch - key) % MAX_KEY_VAL);
    }
}
