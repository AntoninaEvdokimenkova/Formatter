import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class FormatterTest {
    private String inputName;

    private String outputName;

    private String italonName;

    private Formatter formatter;

    private IReader iReaderUse;

    private IReader iReader;

    private IWriter iWriter;

    private IReader iItalon;

    @Before
    public void setUp() {

        this.inputName = "src/test/resources/input";

        this.outputName = "src/test/resources/output";

        this.italonName = "src/test/resources/italon";

        this.iReader = new ReaderFile(this.outputName);

        this.iReaderUse = new ReaderFile(this.inputName);

        this.iWriter = new WriterFile(this.outputName);

        this.iItalon = new ReaderFile(this.italonName);

        this.formatter  = new Formatter(this.iReaderUse, this.iWriter);

    }

    @Test
    public void format() throws Exception {
        char readerLetter = ' ';
        char italonLetter = ' ';

        formatter.format();

        while ((!iReader.isEOF()) && (!iItalon.isEOF())) {
            readerLetter = iReader.getCurrentLetter();
            italonLetter = iItalon.getCurrentLetter();
            System.out.println(readerLetter + "  " + italonLetter);
            if (readerLetter != italonLetter) {
                assertFalse("wrong formatting", true);
            }
        }

        if ((!iReader.isEOF()) || (!iItalon.isEOF())) {
            assertFalse("wrong formatting", true);
        }
    }

}