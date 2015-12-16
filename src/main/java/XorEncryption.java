
public class XorEncryption extends EncryptionAlgorithmSuper{

    public XorEncryption(){
        charEncryptOp=new EncryptChar();
        charDecryptOp = new EncryptChar();
    }

    public class EncryptChar implements CharIntOperator {
        public char operate(char ch, int key) throws InvalidEncryptionKeyException{
            checkKey(key);
            return (char)(ch^key);
        }
    }
}
