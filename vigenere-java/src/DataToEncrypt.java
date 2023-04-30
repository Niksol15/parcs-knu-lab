import java.io.Serializable;
import java.lang.String;

public class DataToEncrypt implements Serializable {
    public final String plaintext;
    public final String key;

    public DataToEncrypt(String plaintext, String key) {
        this.plaintext = plaintext;
        this.key = key;
    }
}