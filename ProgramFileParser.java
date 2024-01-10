import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ProgramFileParser {

    static String readFile(String pathname) throws IOException {
        File file = new File(pathname);
        StringBuilder fileContents = new StringBuilder((int)file.length());        

        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + "\n");
            }
            return removeBlankLines(fileContents.toString());
        }
    }

    static int getNumberOfLinesWithContent(String s) {
        s = removeBlankLines(s);  
        int s_len = s.length();
        int cnt = 0;
        for (int i = 0; i < s_len; ++i) {
            if (s.charAt(i) == '\n') {
                ++cnt;
            }
        }
        return cnt + 1;
    }
    static String getNthLineWithContent(int n, String s) {
        return removeBlankLines(s).split("\n")[n - 1];
    }
    /*
    public static void main(String[] args) throws IOException {
        //String s = getNthLineWithContent(4, readFile("Program.txt"));
        String s = readFile("Program.txt");
        System.out.println(getNthLineWithContent(2, s));
    }
    */

    private static String removeBlankLines(String s) {
        s = s.replaceAll("\n+", "\n");
        if (s.endsWith("\n")) s = s.substring(0, s.length() - 1);
        return s;
    }

}
