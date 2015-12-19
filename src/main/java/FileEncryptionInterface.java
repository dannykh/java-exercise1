import java.io.*;

public class FileEncryptionInterface {
    private InputStream inputStream;
    private OutputStream outputStream;
    private OutputStream errorStream;

    private enum EncryptorMode {ENCRYPT, DECRYPT}

    public FileEncryptionInterface(InputStream inStream,
                                   OutputStream outStream,
                                   OutputStream errStream) {
        inputStream = inStream;
        outputStream = outStream;
        errorStream = errStream;
    }

    private static String getOutputFileName(String inputFileName, EncryptorMode mode) {
        String postfix = (mode == EncryptorMode.ENCRYPT) ? "_encrypted" : "_decrypted";
        int p = inputFileName.lastIndexOf('.');
        if (p <= 0) {
            return inputFileName + postfix;
        }
        String name = inputFileName.substring(0, p);
        String ext = inputFileName.substring(p + 1);
        return name + postfix + "." + ext;
    }

    public void encrypt(FileEncryptor alg,
                        String inputFilePath,
                        int key) throws IOException {
        try {
            File inputFile = new File(inputFilePath);
            String keyPath = inputFile.getParent() + "\\key.txt";
            FileWriter keyFileStream = new FileWriter(keyPath);
            keyFileStream.write(String.format("%d", key));
            keyFileStream.close();
            alg.encrypt(inputFilePath, getOutputFileName(inputFilePath, EncryptorMode.ENCRYPT)
                    , key);
        } catch (IOException e) {
            errorStream.write("Error handling input file.".getBytes());
        } catch (InvalidEncryptionKeyException e) {
            errorStream.write("Invalid key.".getBytes());
        }
    }

    public void decrypt(FileEncryptor alg,
                        String inputFilePath,
                        String keyPath) throws IOException {
        try {
            alg.decrypt(inputFilePath, getOutputFileName(inputFilePath,
                    EncryptorMode.DECRYPT), keyPath);
        } catch (IOException e) {
            errorStream.write("Error handling input file.".getBytes());
        } catch (InvalidEncryptionKeyException e) {
            errorStream.write("Invalid key.".getBytes());
        }
    }
}
