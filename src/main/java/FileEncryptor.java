import java.io.*;

class InvalidFilePathException extends RuntimeException {
    public InvalidFilePathException() {
        super();
    }

    public InvalidFilePathException(String path) {
        super(path + "\\ Is not a valid file path.");
    }
}

public class FileEncryptor {
    private EncryptionAlgorithm encryptionAlg;

    private enum ParserMode {ENCRYPT, DECRYPT}

    public FileEncryptor(EncryptionAlgorithm encryptionAlgorithm) {
        encryptionAlg = encryptionAlgorithm;
    }


    private int getKey(String keyFilePath) throws IOException {
        BufferedReader inputStream = null;
        int key = -1;
        FileReader fr = null;
        try {
            fr = new FileReader(keyFilePath);
            inputStream = new BufferedReader(fr);
            key = Integer.parseInt(inputStream.readLine(), 10);
            System.out.println(key);
        } finally {
            if (fr != null) {
                fr.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return key;
    }

    /**
     * parseFile : Parses input file according to mode of activation, and
     * outputs
     * to result file.
     *
     * @param readFilePath  : File to read from.
     * @param writeFilePath : File to write to.
     * @param key           : The key to use.
     * @param mode          : ENCRYPT for encryption, DECRYPT for decryption.
     */
    private void parseFile(String readFilePath, String writeFilePath,
                           int key, ParserMode mode) throws IOException {
        FileReader inputStream = null;
        FileWriter outputStream = null;
        try {
            inputStream = new FileReader(readFilePath);
            outputStream = new FileWriter(writeFilePath);
            int c;
            char parsed;
            while ((c = inputStream.read()) != -1) {
                parsed = (mode == ParserMode.ENCRYPT)
                        ? encryptionAlg.encryptChar((char) c, key)
                        : encryptionAlg.decryptChar((char) c, key);
                outputStream.write(parsed);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }

    }

    public void encrypt(String originalFilePath,
                        String outputFilePath, String keyPath) throws IOException {
        int key = getKey(keyPath);
        parseFile(originalFilePath, outputFilePath, key, ParserMode.ENCRYPT);
    }

    public void encrypt(String originalFilePath,
                        String outputFilePath, int key) throws IOException,
            InvalidEncryptionKeyException, InvalidFilePathException {
        parseFile(originalFilePath, outputFilePath, key, ParserMode.ENCRYPT);
    }

    public void decrypt(String encryptedFilePath, String outputFilePath,
                        String keyPath) throws IOException {
        int key = getKey(keyPath);
        parseFile(encryptedFilePath, outputFilePath, key, ParserMode.DECRYPT);
    }

    public void decrypt(String encryptedFilePath, String outputFilePath,
                        int key) throws IOException {
        parseFile(encryptedFilePath, outputFilePath, key, ParserMode.DECRYPT);
    }
}
