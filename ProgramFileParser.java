import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ProgramFileParser {

    static String readFile(String pathname) throws IOException {
        File file = new File(pathname);
        StringBuilder fileContents = new StringBuilder((int)file.length());        

        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + System.lineSeparator());
            }
            return fileContents.toString();
        }
    }


    // get Nth line from a String that has many lines (you can get the first line
    // by looping through the string and breaking when seeing the first line break '\n')
    static String getNthLineFromString(int n, String content) {
        int cnt = n;
        String ret = "";
        int content_length = content.length();

        for (int i = 0; i < content_length; ++i) {
            if (content.charAt(i) == '\n') {
                --cnt;
                continue;
            }
            if (cnt == 1) {
                ret += content.charAt(i);
            }
            if (cnt == 0) {
                return ret;
            }
        }
        return "WEIRD";
    }

    static int getNumberOfLinesWithContent(String content) {
        int content_length = content.length();
        int count = 0;

        for (int i = 0; i < content_length; ++i) {
            try {
                if (content.charAt(i) == '\n' && content.charAt(i+1) != '\n') {
                    ++count;
                }
            }
            catch (IndexOutOfBoundsException e) {}
        }
        return count + 1;
    }

}
