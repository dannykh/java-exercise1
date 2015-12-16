import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Use : java Encryptor1_P1 [flag] [input file] [output file] [key file]
 * flag : -E for encryption. -D for decryption.
 * key file is required only in case of decryption.
 */
public class Encryptor1_P1 {
    private enum Mode {ENCRYPT, DECRYPT}

    private enum ProcResult {SUCCESS, FAIL}

    public static Mode getMode(String arg) {
        String normArg = arg.toLowerCase();
        if ("-e".equals(normArg)) {
            return Mode.ENCRYPT;
        }
        if ("-d".equals(normArg)) {
            return Mode.DECRYPT;
        }
        return null;
    }

    public static String getOutputFileName(String inputFileName, Mode mode) {
        String postfix = (mode == Mode.ENCRYPT) ? "_encrypted" : "_decrypted";
        int p = inputFileName.lastIndexOf('.');
        if (p <= 0) {
            return inputFileName + postfix;
        }
        String name = inputFileName.substring(0, p);
        String ext = inputFileName.substring(p + 1);
        return name + postfix + "." + ext;
    }

    public static ProcResult handleEncryption(File inputFile,
            String outputFileName,
            FileEncryptor encryptor) {

        String keyPath = inputFile.getParent() + "\\key.txt";
        int key = generateKey();
        try {
            encryptor.encryptFile(inputFile.toString(), outputFileName, key);
            FileWriter outputStream = new FileWriter(keyPath);
            outputStream.write(String.format("%d", key));
            outputStream.close();
        } catch (InvalidEncryptionKeyException ex) {
            System.out.println("Invalid key.");
            return ProcResult.FAIL;
        } catch (InvalidFilePathException ex) {
            System.out.println("Error handling input file.");
            return ProcResult.FAIL;
        } catch (IOException ex) {
            System.out.println("Error handling input file.");
            return ProcResult.FAIL;
        }

        return ProcResult.SUCCESS;
    }

    public static ProcResult handleDecryption(File inputFile,
            String outputFileName,String keyPath,FileEncryptor encryptor) {
        try {
            encryptor.decryptFile(inputFile.toString(), outputFileName,
                    keyPath);

        } catch (InvalidEncryptionKeyException ex) {
            System.out.println("Invalid key.");
            return ProcResult.FAIL;
        } catch (InvalidFilePathException ex) {
            System.out.println("Error handling input file.");
            return ProcResult.FAIL;
        } catch (IOException ex) {
            System.out.println("Error handling input file.");
            return ProcResult.FAIL;
        }

        return ProcResult.SUCCESS;
    }


    public static int generateKey() {
        return (int) (Math.random() * 10) + 1;
    }

    public static void main(String args[]) {
        if (args.length < 2 || args.length > 3) {
            System.out.println("Invalid call.");
            return;
        }
        Mode mode = getMode(args[0]);
        if (mode == null) {
            System.out.println("Illegal flags.");
            return;
        }
        if (mode == Mode.DECRYPT && args.length < 3) {
            System.out.println("No key file provided.");
            return;
        }
        File inputFile = new File(args[1]);
        if (!inputFile.exists() || !inputFile.canRead()) {
            System.out.println("Invalid input file.");
            return;
        }
        String outputFileName = inputFile.getParent() + "\\"
                + getOutputFileName(inputFile.getName(), mode);
        System.out.println(outputFileName);
        FileEncryptor encryptor = new FileEncryptor(new ShiftUpEncryption());
        if (mode == Mode.ENCRYPT) {
            if (handleEncryption(inputFile, outputFileName, encryptor)
                    != ProcResult.SUCCESS) {
                return;
            }
        }

        if (mode == Mode.DECRYPT) {
            if (handleDecryption(inputFile, outputFileName, args[2],
                    encryptor) != ProcResult.SUCCESS) {
                return;
            }
        }

    }
}
