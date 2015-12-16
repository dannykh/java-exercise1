import java.io.File;
import java.io.IOException;

public class Test1 {
    public static void main(String[] args) throws IOException, InvalidEncryptionKeyException,
    InvalidFilePathException {
        EncryptionAlgorithm al1 = new ShiftUpEncryption();
        EncryptionAlgorithm al2= new RepeatEncryption(al1,1);
        String dir="D:\\work\\OFEK\\prep\\prep_1\\encryptor1\\src\\test";
        String fp1=dir+"\\file1.txt";
        String fp2=dir+"\\file2.txt";
        String fp3=dir+"\\file3.txt";
        String keyPath= dir+"\\key.key";
        File fl= new File(keyPath);
        //System.out.println(fl.getParent().toString() + ", ," + fl.getName());
        String exmp="ab/c/d/efg/file.dsfs.sf";
        System.out.println(exmp.split("."));
        /*
        FileEncryptor enc= new FileEncryptor(al1);
        enc.encryptFile(fp1,fp2,keyPath);
        enc.decryptFile(fp2, fp3, keyPath);
        */

    }
}
