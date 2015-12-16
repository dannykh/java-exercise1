/**
 * EncryptionAlgorithmSuper is what EncryptionAlgorithm should be.
 * This class exists only because we were instructed to make EncryptionAlgorithm
 * an iterface.
 */
public abstract class EncryptionAlgorithmSuper implements EncryptionAlgorithm{
    protected CharIntOperator charEncryptOp;
    protected CharIntOperator charDecryptOp;
    protected int KEY_STRENGTH;
    protected int MIN_KEY_VAL;
    protected int MAX_KEY_VAL;

    public EncryptionAlgorithmSuper(){
        MIN_KEY_VAL=0;
        // MAX_KEY_VAL=Character.MAX_VALUE; // Returns 16 bit value, while actual char behaviour is different
        MAX_KEY_VAL = 127;
        KEY_STRENGTH=(int)Math.ceil(Math.log10(MAX_KEY_VAL));
    }

    protected String parseString(String str,int key,CharIntOperator op)
            throws InvalidEncryptionKeyException{
        checkKey(key);
        String encrypted="";
        for(int i=0;i<str.length();i++){
            encrypted+=(char)(op.operate(str.charAt(i), key));
        }
        return encrypted;
    }

    public void checkKey(int key) throws InvalidEncryptionKeyException{
        if (key<MIN_KEY_VAL || key>MAX_KEY_VAL){
            throw new InvalidEncryptionKeyException("Invalid key size");
        }
    }

    public String encryptString(String str,int key)
            throws InvalidEncryptionKeyException{
        return parseString(str, key, charEncryptOp);
    }

    public String decryptString(String str,int key)
            throws InvalidEncryptionKeyException{
        return parseString(str, key, charDecryptOp);
    }

    public int getKeyStrength(){
        return KEY_STRENGTH;
    }

    public char encryptChar(char ch, int key) throws InvalidEncryptionKeyException{
        return charEncryptOp.operate(ch, key);
    }

    public char decryptChar(char ch, int key) throws InvalidEncryptionKeyException{
        return charDecryptOp.operate(ch, key);
    }

    protected interface CharIntOperator{
        char operate(char ch,int key) throws InvalidEncryptionKeyException;
    }
}
