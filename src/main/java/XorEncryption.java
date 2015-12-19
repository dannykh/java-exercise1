
public class XorEncryption extends EncryptionAlgorithmBase {

    public XorEncryption() {
        charEncryptor = (ch, key) -> (char) ((ch ^ key) % MAX_KEY_VAL);
        charDecryptor = (ch, key) -> (char) ((ch ^ key) % MAX_KEY_VAL);
    }

}
