import java.io.FileWriter;
import java.io.IOException;

public class WriterFile implements IWriter {
    private FileWriter writer;

    WriterFile(final String fileNme) {
        try {
            writer = new FileWriter(fileNme, false);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void pushLetter(final char letter) {
        try {
            writer.append(letter);
            writer.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
