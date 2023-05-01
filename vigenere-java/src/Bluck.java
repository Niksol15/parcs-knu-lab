import java.util.*;
import java.io.*;
import java.nio.file.*;

import parcs.*;

public class Bluck {
    private final static int WORKERS = 2;
    private final static Path KEY_FILENAME = Paths.get("../key.txt");
    private final static Path TEXT_FILENAME = Paths.get("../plaintext.txt");

    public static void main(String[] args) throws Exception {
        var task = new task();
        task.addJarFile("Vigenere.jar");

        String plaintext = Files.readString(TEXT_FILENAME).toLowerCase();
        String key = Files.readString(KEY_FILENAME).toLowerCase();
        var dataByWorker = prepareData(plaintext, key);

        var info = new AMInfo(task, null);
        var channels = new LinkedList<channel>();
        for (var data : dataByWorker) {
            var p = info.createPoint();
            var c = p.createChannel();
            p.execute("Vigenere");
            c.write(data);
            channels.add(c);
        }

        var encrypted = new String();
        System.out.println("Waiting for result...");
        for (var c : channels) {
            var res = (String) c.readObject();
            encrypted += res;
        }
        System.out.println("Result: " + encrypted);
        task.end();
    }

    private static List<DataToEncrypt> prepareData(String plaintext, String key) {
        var result = new LinkedList<DataToEncrypt>();
        var charPerWorker = plaintext.length() / WORKERS;
        int currPos = 0;
        int endPos = 0;
        for (int i = 0; i < WORKERS; ++i) {
            if (currPos + charPerWorker > plaintext.length()) {
                endPos = plaintext.length();
            } else {
                endPos = currPos + charPerWorker;
            }
            var str = plaintext.substring(currPos, endPos);
            System.out.println(str);

            var indx = currPos % key.length();
            var actualKey = key.substring(indx) + key.substring(0, indx);
            System.out.println(actualKey);

            var data = new DataToEncrypt(str, key);
            result.add(data);

            currPos += charPerWorker;
        }

        return result;
    }
}