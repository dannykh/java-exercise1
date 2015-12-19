public class ShiftMultiplyEncryption extends EncryptionAlgorithmBase {

    public ShiftMultiplyEncryption() {
        charEncryptor = (ch, key) -> (char) ((ch * key) % MAX_KEY_VAL);
        charDecryptor = (ch, key) -> (char) ((ch / key) % MAX_KEY_VAL);
    }

}
