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
    static String getNthLineFromString(int n, String lines) {
        /*
         *
         * TO BE IMPLEMENTED
         *
         * 
         */ 
        return "";
    }
}
