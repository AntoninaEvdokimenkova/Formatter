public class Formatter {
    private enum  SpecificLetter {
        TRIANGLE_OPEN('{') {
            @Override
            public boolean outLetters(final FormatLetter formatLetter) {
                formatLetter.setEnterAfter(1);
                formatLetter.setEnterBefore(0);
                formatLetter.setIndentBefore(0);

                int indentAfter = formatLetter.getIndentCurrent() + 1;
                formatLetter.setIndentAfter(indentAfter);

                formatLetter.setIndentCurrent(indentAfter);

                return true;
            }
        },

        TRIANGLE_CLOSE('}') {
            @Override
            public boolean outLetters(final FormatLetter formatLetter) {
                if (formatLetter.getEnterAfter() == 0) {
                    formatLetter.setEnterBefore(1);
                } else {
                    formatLetter.setEnterBefore(0);
                }

                formatLetter.setEnterBefore(1);

                int indentCurrent = formatLetter.getIndentCurrent() - 1;

                formatLetter.setIndentBefore(indentCurrent);

                indentCurrent = indentCurrent - 1;

                if (indentCurrent >= 0) {
                    formatLetter.setIndentCurrent(indentCurrent);
                }
                formatLetter.setIndentAfter(indentCurrent);

                return true;
            }
        },

        SEMICOLON(';') {
            @Override
            public boolean outLetters(final FormatLetter formatLetter) {
                formatLetter.setEnterBefore(0);
                formatLetter.setIndentBefore(0);
                formatLetter.setEnterAfter(1);
                formatLetter.setIndentAfter(formatLetter.getIndentCurrent());

                return true;
            }
        },

        PROBEL(' ') {
            @Override
            public boolean outLetters(final FormatLetter formatLetter) {
                if ((formatLetter.getEnterAfter() > 0) || (formatLetter.getIndentAfter() > 0) || (formatLetter.getLetterLast() == formatLetter.getLetter())
                        || (formatLetter.getLetterLast() == '\n')) {

                    return false;
                }

                formatLetter.setEnterBefore(0);
                formatLetter.setIndentBefore(0);
                formatLetter.setEnterAfter(0);
                formatLetter.setIndentAfter(0);

                return true;
            }
        },

        ENTER('\n') {
            @Override
            public boolean outLetters(final FormatLetter formatLetter) {
                formatLetter.setEnterBefore(0);
                formatLetter.setIndentBefore(0);
                formatLetter.setEnterAfter(0);
                formatLetter.setIndentAfter(0);

                return false;
            }
        };

        private char letter;

        SpecificLetter(final char letter) {
            this.letter = letter;
        }

        char getLetter() {

            return letter;
        }

        public abstract boolean outLetters(FormatLetter formatLetter);

    }


    public void format(final IReader iReader, final IWriter iWriter) {
        final int numberOfSpaces = 4;

        FormatLetter formatLetter = new FormatLetter(' ', 0, 0, 0, 0, 0, ' ');

        int enterBefore;

        int indentBefore;

        int enterAfter;

        int indentAfter;

        char currentLetter;

        boolean b = false;

        boolean access = true;

        while (!iReader.isEOF()) {
            currentLetter = iReader.getCurrentLetter();
            formatLetter.setLetter(currentLetter);

            for (SpecificLetter s : SpecificLetter.values()) {
                if (currentLetter == s.getLetter()) {
                    access = s.outLetters(formatLetter);
                    b = true;
                }
            }

            if (!b) {
                formatLetter.setIndentAfter(0);
                formatLetter.setEnterAfter(0);
                formatLetter.setIndentBefore(0);
                formatLetter.setEnterBefore(0);
            }

            b = false;

            if (access) {
                enterBefore = formatLetter.getEnterBefore();
                indentBefore = formatLetter.getIndentBefore();
                enterAfter = formatLetter.getEnterAfter();
                indentAfter = formatLetter.getIndentAfter();

                for (int i = 0; i < enterBefore; i++) {
                    iWriter.pushLetter('\n');
                }

                for (int i = 0; i < indentBefore * numberOfSpaces; i++) {
                    iWriter.pushLetter(' ');
                }

                iWriter.pushLetter(formatLetter.getLetter());

                for (int i = 0; i < enterAfter; i++) {
                    iWriter.pushLetter('\n');
                }

                for (int i = 0; i < indentAfter * numberOfSpaces; i++) {
                    iWriter.pushLetter(' ');
                }
            }

            access = true;
            formatLetter.setLetterLast(currentLetter);

        }
    }
}