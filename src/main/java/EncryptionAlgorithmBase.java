import java.util.function.BiFunction;

/**
 * EncryptionAlgorithmBase is what EncryptionAlgorithm should be.
 * This class exists only because we were instructed to make EncryptionAlgorithm
 * an interface.
 */
public abstract class EncryptionAlgorithmBase implements EncryptionAlgorithm {
    protected BiFunction<Character, Integer, Character> charEncryptor;
    protected BiFunction<Character, Integer, Character> charDecryptor;
    protected int KEY_STRENGTH;
    protected int MIN_KEY_VAL;
    protected int MAX_KEY_VAL;

    public EncryptionAlgorithmBase() {
        MIN_KEY_VAL = 0;
        // MAX_KEY_VAL=Character.MAX_VALUE; // Returns 16 bit value, while actual char behaviour is different
        MAX_KEY_VAL = 127;
        KEY_STRENGTH = (int) Math.ceil(Math.log10(MAX_KEY_VAL));
    }

    public void checkKey(int key) {
        if (key < MIN_KEY_VAL || key > MAX_KEY_VAL) {
            throw new InvalidEncryptionKeyException("Invalid key size");
        }
    }

    public int getKeyStrength() {
        return KEY_STRENGTH;
    }

    public char transformChar(char ch, int key,
                              BiFunction<Character, Integer, Character> op) {
        checkKey(key);
        return op.apply(ch, key);
    }

    public char encryptChar(char ch, int key) {
        return transformChar(ch, key, charEncryptor);
    }

    public char decryptChar(char ch, int key) {
        return transformChar(ch, key, charDecryptor);
    }
}
