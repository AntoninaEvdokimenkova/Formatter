import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.*;


public class ReaderFileTest {
    private String fileCurrentLetterName;
    private String fileEmptyName;


    @Before
    public void setUp() {
        this.fileCurrentLetterName = "src/test/resources/currentLetter";
        this.fileEmptyName = "src/test/resources/empty";
    }

    @Test
    public void getCurrentLetter() throws Exception {
        ReaderFile reader = new ReaderFile(this.fileCurrentLetterName);
        assertTrue('a' == reader.getCurrentLetter());
    }

    @Test
    public void isEOF() throws Exception {
        ReaderFile reader = new ReaderFile(this.fileEmptyName);
        assertTrue(reader.isEOF());
        reader = new ReaderFile(this.fileCurrentLetterName);
        assertFalse(reader.isEOF());
    }
}