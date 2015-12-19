import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.function.BiFunction;

import static org.junit.Assert.assertEquals;

public abstract class EncryptionAlgorithmTests {

    private EncryptionAlgorithm encryptionAlg;
    private BiFunction<Character, Integer, Character> encryptionFunction;
    private BiFunction<Character, Integer, Character> decryptionFunction;

    @Test
    public void decryptionReversesEncryptionTest() {
        assertEquals("D(E('C',K),K) 'C'", 'C', encryptionAlg
                .decryptChar(
                        encryptionAlg.encryptChar('C', 5), 5));
    }
}
