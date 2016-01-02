import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.BiFunction;

public final class FileIOUtils {
    private FileIOUtils() {
    }

    public static ArrayList<Integer> getIntArrayFromFile(String filePath)
            throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        ArrayList<Integer> intList = new ArrayList<>();
        while (scanner.hasNextInt()) {
            intList.add(scanner.nextInt());
        }

        return intList;
    }

    public static <T> void transformFileByChars(String readFilePath, String writeFilePath,
                                                BiFunction<Character, T, Character>
                                                        charTransformer, T param)
            throws IOException {
        FileReader inputStream = null;
        FileWriter outputStream = null;
        try {
            inputStream = new FileReader(readFilePath);
            outputStream = new FileWriter(writeFilePath);
            int c;
            char parsed;
            while ((c = inputStream.read()) != -1) {
                parsed = charTransformer.apply((char) c, param);
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
}
