import parcs.*;

import java.io.*;

public class Vigenere implements AM {
    private String expandKey(String key, int keySize) {
        var iter = keySize / key.length();
        var residue = keySize % key.length();
        var result = new String();
        for (int i = 0; i < iter; ++i) {
            result += key;
        }
        result += key.substring(0, residue);

        return result;
    }


    private char[][] buildTable() {
        var tableSize = (int)'z' - (int)'a' + 1;
        var table = new char[tableSize][tableSize];

        for (int i = 0; i < tableSize; ++i) {
            for (int j = 0; j < tableSize; ++j) {
                table[i][j] = (char)('a' + (i + j) % tableSize);
            }
        }

        return table;
    }

    private String encrypt(DataToEncrypt data) {
        var expandedKey = expandKey(data.key, data.plaintext.length());
        var table = buildTable();
        var result = new String();
        for(int i = 0; i < data.plaintext.length(); ++i) {
            result += table[data.plaintext.charAt(i) - 'a'][expandedKey.charAt(i) - 'a'];
        }

        return result;
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