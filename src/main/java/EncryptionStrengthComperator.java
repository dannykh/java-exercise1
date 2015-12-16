import java.util.Comparator;

public class EncryptionStrengthComperator
        implements Comparator<EncryptionAlgorithm> {
    public int compare(EncryptionAlgorithm al1, EncryptionAlgorithm al2) {
        return al1.getKeyStrength() - al2.getKeyStrength();
    }
}
