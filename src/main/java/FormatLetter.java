public class FormatLetter {
    private int indentBefore;

    private int indentAfter;

    private int indentCurrent;

    private int enterBefore;

    private int enterAfter;

    private char letter;

    private char letterLast;



    public FormatLetter(final char letter, final int indentBefore, final int indentAfter, final int enterBefore, final int enterAfter, int indentCurrent, char letterLast) {
        this.indentBefore = indentBefore;
        this.indentAfter = indentAfter;
        this.letter = letter;
        this.indentCurrent = indentCurrent;
        this.enterBefore = enterBefore;
        this.enterAfter = enterAfter;
        this.letterLast = letterLast;
    }

    public int getIndentBefore() {
        return indentBefore;
    }

    public void setIndentBefore(final int indentBefore) {
        this.indentBefore = indentBefore;
    }

    public int getIndentAfter() {
        return indentAfter;
    }

    public void setIndentAfter(final int indentAfter) {
        this.indentAfter = indentAfter;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(final char letter) {
        this.letter = letter;
    }

    public int getEnterBefore() {
        return enterBefore;
    }

    public void setEnterBefore(final int enterBefore) {
        this.enterBefore = enterBefore;
    }

    public int getEnterAfter() {
        return enterAfter;
    }

    public void setEnterAfter(final int enterAfter) {
        this.enterAfter = enterAfter;
    }

    public int getIndentCurrent() {
        return indentCurrent;
    }

    public void setIndentCurrent(int indentCurrent) {
        this.indentCurrent = indentCurrent;
    }

    public char getLetterLast() {
        return letterLast;
    }

    public void setLetterLast(char letterLast) {
        this.letterLast = letterLast;
    }
}
