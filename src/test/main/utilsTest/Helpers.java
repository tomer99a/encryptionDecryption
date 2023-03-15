package utilsTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;

public class Helpers {
    public static String buildBigText(int numberCharAtStr) {
        StringBuilder str = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < numberCharAtStr; i++) {
            if (secureRandom.nextInt(6) == 3) {
                str.append(" ");
                continue;
            }
            if (secureRandom.nextInt(20) == 3) {
                str.append(System.lineSeparator());
                continue;
            }
            str.append((char) (secureRandom.nextInt(94) + 33));
        }
        str.append("TOMER!!!");
        return str.toString();
    }

    public static boolean compareTwoFiles(String path1, String path2) {
        if (path1.equals(path2))
            return true;
        File file1 = new File(path1);
        File file2 = new File(path2);

        try (FileInputStream in1 = new FileInputStream(file1); FileInputStream in2 = new FileInputStream(file2)) {
            int tmpChar1, tmpChar2;

            while ((tmpChar1 = in1.read()) != -1) {
                tmpChar2 = in2.read();
                if (tmpChar2 == -1)
                    return false;
                if (tmpChar1 != tmpChar2)
                    return false;
            }
            tmpChar2 = in2.read();
            return tmpChar2 == -1;
        } catch (IOException e) {
            System.err.println("fail at compare two files");
            return false;
        }
    }
}
