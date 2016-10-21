import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class OutputFile implements Output{
    FileWriter writer;

    OutputFile(String fileNme) {
        try {
            writer = new FileWriter(fileNme, false);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void pushLetter(char letter) {
        try {
            writer.append(letter);
            writer.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
