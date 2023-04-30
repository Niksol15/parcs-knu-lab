import parcs.*;
import java.io.*;

public class Vigenere implements AM {

    String encrypt(DataToEncrypt data) {
        return data.plaintext;
    }


    public void run(AMInfo info) {
        var data = (DataToEncrypt) info.parent.readObject();
        System.out.println("key = " + data.key);
        System.out.println("plaintext = " + data.plaintext);
        String encrypted = encrypt(data);

        System.out.println("Encrypted data: " + encrypted);
        info.parent.write(encrypted);
    }
}