import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Formatter formatter  = new Formatter();
        try {
            formatter.format();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
