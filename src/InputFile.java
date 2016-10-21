import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputFile implements Input{
    FileReader reader;
    int c;
    char cOut;
    boolean eof;

    InputFile(String fileNme) {
        try {
            reader = new FileReader(fileNme);

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        eof = false;
        cOut = ' ';

        try {
            c = reader.read();
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }

        if (c  == -1) {
            eof = true;
        }
    }

    @Override
    public char getCurrentLetter() {
        if (c != -1) {
            cOut = (char) c;
        } else {
            cOut = ' ';
        }

        try {
            c = reader.read();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (c  == -1) {
            eof = true;
        }

        return cOut;
    }

    @Override
    public boolean isEOF() {

        return eof;
    }
}
