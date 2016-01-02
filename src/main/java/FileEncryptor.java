import java.io.*;

public class FileEncryptor {
    private EncryptionAlgorithm encryptionAlg;

    public FileEncryptor(EncryptionAlgorithm encryptionAlgorithm) {
        encryptionAlg = encryptionAlgorithm;
    }

    public void encrypt(String originalFilePath,
                        String outputFilePath, int key) throws IOException {
        FileIOUtils.transformFileByChars(originalFilePath, outputFilePath,
                (ch, x) -> encryptionAlg.encryptChar(ch, x), key);
    }

    public void decrypt(String encryptedFilePath, String outputFilePath,
                        int key) throws IOException {
        FileIOUtils.transformFileByChars(encryptedFilePath, outputFilePath,
                (ch, x) -> encryptionAlg.encryptChar(ch, x), key);
    }
}
