import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;


public class WriterFileTest {
    private FileReader reader;

    private String fileName;

    private WriterFile writerFile;


    @Before
    public void setUp() {

        this.fileName = "src/test/resources/writeLetter";

        this.writerFile = new WriterFile(this.fileName);

        try {
            this.reader = new FileReader(this.fileName);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void pushLetter() throws Exception {
        char letter = 'a';

        char letterRead = ' ';

        writerFile.pushLetter(letter);

        try {
            letterRead = (char)reader.read();
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }

        if (letterRead != letter) {
            assertFalse("wrong writer", true);
        }
    }

}