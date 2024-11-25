import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class FileMismatchExample {
    public static void main(String[] args) throws IOException {
        // Paths to the two files you want to compare
        String file1 = "file1.txt";
        String file2 = "file2.txt";

        // Compare the files
        long mismatchIndex = Files.mismatch(Paths.get(file1), Paths.get(file2));

        if (mismatchIndex == -1) {
            System.out.println("The files are identical.");
        } else {
            System.out.println("The files differ at byte index: " + mismatchIndex);
        }
    }
}
