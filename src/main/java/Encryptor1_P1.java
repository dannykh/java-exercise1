import java.io.*;

/**
 * Use : java Encryptor1_P1 [flag] [input file] [output file] [key file]
 * flag : -E for encryption. -D for decryption.
 * key file is required only in case of decryption.
 */
public class Encryptor1_P1 {
    private enum Mode {ENCRYPT, DECRYPT}

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
        String inputFilePath = args[1];
        FileEncryptor fileEncryptor = new FileEncryptor(new ShiftUpEncryption());
        FileEncryptionInterface encryptor = new FileEncryptionInterface(System.in,
                System.out, System.err);
        try {
            if (mode == Mode.ENCRYPT) {
                encryptor.encrypt(fileEncryptor, inputFilePath, generateKey());
            } else {
                encryptor.decrypt(fileEncryptor, inputFilePath, args[2]);
            }
        } catch (IOException e) {
            System.out.println("Cannot Write to stream.");
        }

    }
}
