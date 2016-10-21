import java.io.IOException;

public class Formatter {

    public final int numberOfSpaces = 4;

    private int indent;

    private Input input;

    private Output output;

    private char currentLetter;

    private char lastLetter;

    Formatter(String inputName, String outputName){
        input = new InputFile(inputName);
        output = new OutputFile(outputName);
        currentLetter = ' ';
        lastLetter = ' ';
        indent = 0;
    }


    public enum  SpecificLetter {
        TRIANGLE_OPEN ('{') {
            @Override
            public int outLetters(Output output, int indent, int numberOfSpaces) {
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
            public int outLetters(Output output, int indent, int numberOfSpaces) {
                if (indent > 0) {
                    indent--;
                }

                    output.pushLetter('\n');
                    for (int i = 0; i < indent * numberOfSpaces; i++) {
                        output.pushLetter(' ');
                    }

                output.pushLetter('}');



                return indent;
            }
        },

        SEMICOLON (';'){
            @Override
            public int outLetters(Output output, int indent, int numberOfSpaces) {
                output.pushLetter(';');

                output.pushLetter('\n');

                for (int i = 0; i < indent * numberOfSpaces; i++) {
                    output.pushLetter(' ');
                }


                return indent;
            }
        },

        PROBEL (' '){
            @Override
            public int outLetters(Output output, int indent, int numberOfSpaces) {

                return indent;
            }
        },

        ENTER ('\n'){
            @Override
            public int outLetters(Output output, int indent, int numberOfSpaces) {

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

        public abstract int outLetters(Output output, int indent, int numberOfSpaces);

    }


    public void format() {

        boolean b = false;
            while (!input.isEOF()) {
                currentLetter = input.getCurrentLetter();

                for (SpecificLetter s : SpecificLetter.values()) {
                    if (currentLetter == s.getLetter()) {
                        indent = s.outLetters(output, indent, numberOfSpaces);
                        b = true;
                    }

                }

                if (!b) {
                    output.pushLetter(currentLetter);
                }
                b = false;
        }
    }
}
