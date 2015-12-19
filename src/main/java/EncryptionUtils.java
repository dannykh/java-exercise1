import java.util.function.BiFunction;

public final class EncryptionUtils {

    private EncryptionUtils() {
    }

    public static String encryptString(String str, int key, EncryptionAlgorithm alg) {
        return parseString(str, key, alg::encryptChar);
    }

    public static String decryptString(String str, int key, EncryptionAlgorithm alg) {
        return parseString(str, key, alg::decryptChar);
    }

    public static String parseString(String str, int key,
                                     BiFunction<Character, Integer, Character> op) {
        return String.valueOf(str.chars().mapToObj(c -> op.apply((char) c, key)));
    }
}
