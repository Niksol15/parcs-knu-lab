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
        System.out.println("Expanded key = " + result);

        return result;
    }


    private char[][] buildTable() {
        var tableSize = (int) 'z' - (int) 'a' + 1;
        var table = new char[tableSize][tableSize];

        for (int i = 0; i < tableSize; ++i) {
            for (int j = 0; j < tableSize; ++j) {
                table[i][j] = (char) ('a' + (i + j) % tableSize);
            }
        }

        return table;
    }

    private String encrypt(DataToEncrypt data) {
        var expandedKey = expandKey(data.key, data.plaintext.length());
        var table = buildTable();
        var result = new String();
        for (int i = 0; i < data.plaintext.length(); ++i) {
            int x = (int) data.plaintext.charAt(i) - (int) 'a';
            int y = (int) expandedKey.charAt(i) - (int) 'a';
            System.out.format("table[x = %d] = " + data.plaintext.charAt(i) + "\n", x);
            System.out.format("table[y = %d] = " + expandedKey.charAt(i) + "\n", y);
            System.out.format("table[%d][%d] = " + table[x][y] + "\n", x, y);
            result += table[x][y];
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