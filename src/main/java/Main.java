public class Main {
    public static void main(String[] args) {
        String inputName = "input";

        String outputName = "output";

        IReader readerFile = new ReaderFile(inputName);

        IWriter writerFile = new WriterFile(outputName);

        Formatter formatter  = new Formatter();

        formatter.format(readerFile, writerFile);

    }
}
