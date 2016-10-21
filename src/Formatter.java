import java.io.IOException;

public class Formatter {

    public final int numberOfSpaces = 4;

    private int indent;

    private Input input;

    private Output output;

    private char currentLetter;

    private char lastLetter;

    Formatter(){
        input = new InputFile("input");
        output = new OutputFile("output");
        currentLetter = ' ';
        lastLetter = ' ';
        indent = 0;
    }


    public enum  SpecificLetter {
        TRIANGLE_OPEN ('{') {
            @Override
            public int outLetters(Output output, int indent, int numberOfSpaces, char lastLetter) {
                indent++;
                output.pushLetter('{');
                output.pushLetter('\n');

                for (int i = 0; i < indent * numberOfSpaces; i++) {
                    output.pushLetter(' ');
                }

                return indent;
            }
        },

        TRIANGLE_CLOSE ('}'){
            @Override
            public int outLetters(Output output, int indent, int numberOfSpaces, char lastLetter) {
                if (indent > 0) {
                    indent--;
                }

                if ((lastLetter != '}') && (lastLetter != ';')) {
                    output.pushLetter('\n');
                    for (int i = 0; i < indent * numberOfSpaces; i++) {
                        output.pushLetter(' ');
                    }
                }
                output.pushLetter('}');
                output.pushLetter('\n');

                for (int i = 0; i < indent * numberOfSpaces; i++) {
                    output.pushLetter(' ');
                }

                return indent;
            }
        },

        ENTER (';'){
            @Override
            public int outLetters(Output output, int indent, int numberOfSpaces, char lastLetter) {
                output.pushLetter(';');
                output.pushLetter('\n');

                for (int i = 0; i < indent * numberOfSpaces; i++) {
                    output.pushLetter(' ');
                }

                return indent;
            }
        };

        private char letter;

        SpecificLetter(char letter) {
            this.letter = letter;
        }

        char getLetter () {

            return letter;
        }

        public abstract int outLetters(Output output, int indent, int numberOfSpaces, char lastLetter);

    }


    public void format() throws IOException {

        while (!input.isEOF()) {
            lastLetter = currentLetter;
            currentLetter = input.getCurrentLetter();
            
            output.pushLetter(currentLetter);

            for (SpecificLetter s : SpecificLetter.values()) {
                if (currentLetter == s.getLetter()) {
                    indent = s.outLetters(output, indent, numberOfSpaces, lastLetter);

                    break;
                }

            }
        }
    }
}
