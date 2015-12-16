

public final class EncryptionUtils {

    private EncryptionUtils(){}

    interface EncryptChar<KeyType>{
        char operate(char ch,KeyType key);
    }

    public static <KeyType> String genericStringEncrypt(String str,KeyType key,
                                                        EncryptChar op){
        String encrypted="";
        for(int i=0;i<str.length();i++){
            encrypted+=(char)(op.operate(str.charAt(i), key));
        }
        return encrypted;
    }
}
